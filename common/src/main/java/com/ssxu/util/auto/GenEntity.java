package com.ssxu.util.auto;

import java.sql.*;

/**
 * POJO Product
 * @author  Tumi
 * 日期：2012-10-10
 */
public class GenEntity {
     
    //数据库连接
    private static final String URL ="jdbc:mysql://localhost:3306/za_edu_cloud_platform_auth";
    private static final String NAME = "root";
    private static final String PASS = "666666";
    private static final String DRIVER ="com.mysql.jdbc.Driver";
 
    /**
     * 通过表名自动生实体类
     * @param tableName 表名
     * @param type xml 生成xml需要的字符串  entity 生成实体类需要是字符串
     * @param entityPath xml 生成需要的新增那栏的实体类路径
     * @return
     */
    public static String entityStr(String tableName,String type,String entityPath){

        String[] colnames; // 列名数组
        String[] colTypes; //列名类型数组
        int[] colSizes; //列名大小数组
        StringBuilder s = new StringBuilder();
        
    	 //创建连接
        Connection con;
        //查要生成实体类的表
        String sql = "select * from " + tableName;
        PreparedStatement pStemt = null;
        try {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            con = DriverManager.getConnection(URL,NAME,PASS);
            pStemt = con.prepareStatement(sql);
            ResultSetMetaData rsmd = pStemt.getMetaData();
            int size = rsmd.getColumnCount();   //统计列
            colnames = new String[size];
            colTypes = new String[size];
            colSizes = new int[size];
            for (int i = 0; i < size; i++) {
                colnames[i] = rsmd.getColumnName(i + 1).toLowerCase();
                colTypes[i] = rsmd.getColumnTypeName(i + 1);
                colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
            }
          if (type.equals("entity")){  //生成实体类需要的字符串
	          //设置属性
	          for (int i=0;i<colnames.length;i++){
	        	  String col = colnames[i];
	        	  String name = "";
	        	  if (col.indexOf("_") != -1){
	        		  //存在好多个横线的情况 所以需要循环去吧第一个字母转大写
	        		  name = col.split("_")[0];
	        		  String[] cols = col.split("_");
	        		  for (int h = 1;h < cols.length; h++){
	        			  name += initcap(cols[h]);
	        		  }
	        	  } else {
	        		  name = col.split("_")[0];
	        	  }
	        	  s.append("    private "+sqlType2JavaType(colTypes[i]) +" "+ name +";\r\n");
	          }
	          //设置setget方法
	          for (int i=0;i<colnames.length;i++){
	        	  String col = colnames[i];
	        	  String setName = "";
	        	  String setSx = "";
	        	  if (col.indexOf("_") != -1){
	        		  setName = col.split("_")[0];
	        		  setSx = initcap(col.split("_")[0]);
	        		  String[] cols = col.split("_");
	        		  for (int h = 1;h < cols.length; h++){
	        			  setName += initcap(cols[h]);
	        			  setSx += initcap(cols[h]);
	        		  }
	        	  } else {
	        		  setName = col.split("_")[0];
	        		  setSx = initcap(col.split("_")[0]);
	        	  }
	        	  s.append("    public void set"+setSx+"("+sqlType2JavaType(colTypes[i])+" "+setName+") {\r\n");
	        	  s.append("        this."+setName+" = " + setName+";\r\n");
	        	  s.append("    }\r\n\r\n");
	        	  s.append("    public "+sqlType2JavaType(colTypes[i])+" get"+setSx+"(){\r\n");
	        	  s.append("        return " + setName +";\r\n");
	        	  s.append("    }\r\n\r\n");
	          }
          } else {  //生成xml需要的字符串
        	  s.append("    <resultMap id=\"BaseResultMap\" type=\"[entityPackage].[entityClass]\">\r\n");
        	  String id = "";
        	  StringBuilder fildWhere = new StringBuilder();
        	  StringBuilder insertCloumn = new StringBuilder("		<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >\r\n");
        	  StringBuilder insertValue = new StringBuilder(" 		<trim prefix=\"values(\" suffix=\")\" suffixOverrides=\",\" >\r\n");
        	  StringBuilder updateSet = new StringBuilder("		<trim prefix=\"set\" suffixOverrides=\",\">\r\n");
        	  for (int i=0;i<colnames.length;i++){
        		  String col = colnames[i];
        		  if (i == 0){
        			  id = col.toUpperCase();
        		  }
        		  String name = "";
	        	  if (col.split("_").length == 2){
	        		  name = col.split("_")[0]+initcap(col.split("_")[1]);
	        	  } else if(col.split("_").length == 3){
	        		  name = col.split("_")[0]+initcap(col.split("_")[1]+initcap(col.split("_")[2]));
	        	  } else {
	        		  name = col.split("_")[0];
	        	  }
	        	  String resultMap = i == 0 ? "id" : "result";
            	  s.append("        <"+resultMap+" column=\""+col.toUpperCase()+"\" property=\""+name+"\" jdbcType=\""+sqlType2JavaTypeXml(colTypes[i])+"\" />\r\n");
            	  String wherename = "";
	        	  if (col.split("_").length == 2){
	        		  wherename = col.split("_")[0]+initcap(col.split("_")[1]);
	        	  } else if(col.split("_").length == 3){
	        		  wherename = col.split("_")[0]+initcap(col.split("_")[1]+initcap(col.split("_")[2]));
	        	  } else {
	        		  wherename = col.split("_")[0];
	        	  }
	        	  String dohao = i < colnames.length -1 ? "," : "";
            	  fildWhere.append("			<if test=\""+wherename+" != null and "+wherename+" != ''\">\r\n");
            	  fildWhere.append("				and "+col.toUpperCase()+" LIKE CONCAT('%',#{"+wherename+"},'%')\r\n");
            	  fildWhere.append("			</if>\r\n");
            	  insertCloumn.append("			<if test=\""+wherename+" != null and "+wherename+" != ''\">\r\n");
            	  insertCloumn.append("				"+col.toUpperCase()+""+dohao+"\r\n");
            	  insertCloumn.append("			</if>\r\n");
            	  insertValue.append("			<if test=\""+wherename+" != null and "+wherename+" != ''\">\r\n");
            	  insertValue.append(" 				#{"+wherename+",jdbcType="+sqlType2JavaTypeXml(colTypes[i])+"}"+dohao+"\r\n");
            	  insertValue.append("			</if>\r\n");
            	  if (i != 0){
            		  updateSet.append("			<if test=\""+wherename+" != null and "+wherename+" != ''\">"+col.toUpperCase()+"=#{"+wherename+"}"+dohao+"</if>\r\n");
            	  }
              } 
        	  insertCloumn.append("		</trim>\r\n");
        	  insertValue.append("		</trim>\r\n");
        	  updateSet.append("		</trim>\r\n");
        	  s.append("    </resultMap>\r\n\r\n");
        	  s.append("    <!-- list查询 -->\r\n");
        	  s.append("    <select id=\"getList\"  parameterType=\"java.util.Map\" resultMap=\"BaseResultMap\">\r\n");
        	  s.append("        SELECT id FROM "+tableName.toLowerCase()+" <include refid=\"findWhere\" />\r\n");
        	  //s.append("		LIMIT #{pagestart},#{pagesize}\r\n");
        	  s.append("    </select>\r\n\r\n");
        	  s.append("	<select id=\"getCount\" parameterType=\"java.util.Map\" resultType=\"java.lang.Integer\">\r\n");
        	  s.append("		SELECT COUNT("+id+") as cont FROM "+tableName.toLowerCase()+" <include refid=\"findWhere\" />\r\n");
        	  s.append("	</select>\r\n\r\n");
        	  s.append("    <!-- where 条件 -->\r\n");
        	  s.append("    <sql id=\"findWhere\">\r\n");
        	  s.append("        <where>\r\n");
        	  s.append(fildWhere.toString());
        	  s.append("        </where>\r\n");
        	  s.append("    </sql>\r\n\r\n");
        	  s.append("    <!-- 新增 -->\r\n");
        	  s.append("	<insert id=\"save\" parameterType=\""+entityPath+"\" >\r\n");
        	  s.append("		INSERT INTO "+tableName.toLowerCase()+"\r\n");
        	  s.append(insertCloumn.toString());
        	  s.append(insertValue.toString()+"");
        	  s.append("	</insert>\r\n\r\n");
        	  s.append("	<!--修改-->\r\n");
        	  s.append("	<update id=\"update\"  parameterType=\""+entityPath+"\">\r\n");
        	  s.append("		UPDATE "+tableName.toLowerCase()+"\r\n");
        	  s.append(updateSet.toString());
        	  if (id.toLowerCase().split("_").length > 1){
				  s.append("		WHERE "+id+"=#{"+id.toLowerCase().split("_")[0]+initcap(id.toLowerCase().split("_")[1])+"}\r\n");
			  } else {
				  s.append("		WHERE "+id+"=#{"+id.toLowerCase().split("_")[0]+"}\r\n");
			  }
        	  s.append("	</update>\r\n\r\n");
        	  s.append("    <!-- 删除 -->\r\n");
        	  s.append("    <delete id=\"deleteByIds\" parameterType=\"java.lang.String\"> \r\n");
        	  s.append("        DELETE FROM "+tableName.toLowerCase()+" WHERE "+id+" IN \r\n");
        	  s.append("        <foreach collection=\"array\" item=\"id\" open=\"(\" separator=\",\" close=\")\"> \r\n");
        	  s.append("            #{id}\r\n");
        	  s.append("        </foreach>\r\n");
        	  s.append("    </delete>\r\n\r\n");
        	  s.append("    <!-- 通过id返回对象-->\r\n");
        	  s.append("	<select id=\"getObjById\" parameterType=\"java.lang.String\" resultMap=\"BaseResultMap\">\r\n");
        	  s.append("		SELECT * FROM "+tableName.toLowerCase()+" WHERE "+id+" = #{id}\r\n");
        	  s.append("	</select>\r\n");
          }
             
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
        	//System.err.println("获取setGet出错");
        }
    	return s.toString();
    } 
    
    /**
     * 功能：将输入字符串的首字母改成大写
     * @param str
     * @return
     */
    private static String initcap(String str) {
         
        char[] ch = str.toCharArray();
        if(ch[0] >= 'a' && ch[0] <= 'z'){
            ch[0] = (char)(ch[0] - 32);
        }
         
        return new String(ch);
    }
 
    /**
     * 功能：获得列的数据类型 实体类的
     * @param sqlType
     * @return
     */
    private static String sqlType2JavaType(String sqlType) {
         
        if(sqlType.equalsIgnoreCase("bit")){
            return "boolean";
        }else if(sqlType.equalsIgnoreCase("tinyint")){
            return "byte";
        }else if(sqlType.equalsIgnoreCase("smallint")){
            return "short";
        }else if(sqlType.equalsIgnoreCase("int")){
            return "int";
        }else if(sqlType.equalsIgnoreCase("bigint")){
            return "long";
        }else if(sqlType.equalsIgnoreCase("float")){
            return "float";
        }else if(sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric") 
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money") 
                || sqlType.equalsIgnoreCase("smallmoney")){
            return "double";
        }else if(sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char") 
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar") 
                || sqlType.equalsIgnoreCase("text")){
            return "String";
        }else if(sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("timestamp") || sqlType.equalsIgnoreCase("date")){
            return "Date";
        }else if(sqlType.equalsIgnoreCase("image")){
            return "Blod";
        }
         
        return null;
    }
    
    private static String sqlType2JavaTypeXml(String sqlType) {
    	  if(sqlType.equalsIgnoreCase("int")){
              return "INTEGER";
          }else if(sqlType.equalsIgnoreCase("varchar") 
                  || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar") 
                  || sqlType.equalsIgnoreCase("text")){
              return "VARCHAR";
          }else if (sqlType.equalsIgnoreCase("char")){
        	  return "CHAR";
          }else if(sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("timestamp") || sqlType.equalsIgnoreCase("date")){
        	  return "TIMESTAMP";
          }
    	return null;
    }
}