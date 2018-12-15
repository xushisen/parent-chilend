
徐石森
----------------------                                                         
----------------------
springcloud 微服务
-------------
eureka 注册中心  支持集群  默认 yml 8761 server1 8762

common 公共方法

producer 模拟微服务调用的提供方的工程  默认 yml 8888 server1 8887

consumer 模拟微服务调用的调用方的工程  9999

config 配置中心  8901 支持动态刷新  需要安装mq  嫌麻烦的话用docker 一句话搞定

zuul 5000

web 前端项目

service service层项目

dao dao层项目

entity 实体类项目