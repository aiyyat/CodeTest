package com.westpac;

public class Request {
    private String id;
    private String clientId;
    private long timestamp;

    public Request(String id, String clientId, long timestamp) {
        this.id = id;
        this.clientId = clientId;
        this.timestamp = timestamp;
    }
    public Request(String id, String clientId) {
        this(id, clientId, System.currentTimeMillis());
    }


    public String getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;

        Request request = (Request) o;

        if (timestamp != request.timestamp) return false;
        if (id != null ? !id.equals(request.id) : request.id != null) return false;
        return clientId != null ? clientId.equals(request.clientId) : request.clientId == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        return result;
    }
}

