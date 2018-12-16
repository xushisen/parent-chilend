package com.ssxu.sso;

/**
 * 类描述：退出系统的实体类  记录退出系统的信息
 * 创建人：徐石森
 * 创建时间：2018/11/29  13:57
 *
 * @version 1.0
 */
public class Client {
    private final String url;
    private final String token;
    private final String sessionId;

    private Client(Builder builder) {
        this.url = builder.url;
        this.token = builder.token;
        this.sessionId = builder.sessionId;
    }

    public String getUrl() {
        return url;
    }

    public String getToken() {
        return token;
    }

    public String getSessionId() {
        return sessionId;
    }

    public static final class Builder {
        private String url;
        private String token;
        private String sessionId;

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setToken(String token) {
            this.token = token;
            return this;
        }

        public Builder setSessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }
}
