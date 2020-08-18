/*
 * Copyright 1999-2020 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos.common.remote.client;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.remote.request.Request;
import com.alibaba.nacos.api.remote.response.Response;
import com.google.common.util.concurrent.FutureCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * connection on client side.
 *
 * @author liuzunfei
 * @version $Id: Connection.java, v 0.1 2020年08月09日 1:32 PM liuzunfei Exp $
 */
public abstract class Connection {
    
    protected RpcClient.ServerInfo serverInfo;
    
    protected Map<String, String> labels = new HashMap<String, String>();
    
    public Connection(RpcClient.ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }
    
    public String getLabel(String labelKey) {
        return labels.get(labelKey);
    }
    
    public void putLabel(String labelKey, String labelValue) {
        labels.put(labelKey, labelValue);
    }
    
    public void putLabels(Map<String, String> labels) {
        labels.putAll(labels);
    }
    
    /**
     * send request.
     *
     * @param request request.
     * @return
     */
    public abstract Response request(Request request) throws NacosException;
    
    /**
     * send aync request.
     *
     * @param request request.
     */
    public abstract void asyncRequest(Request request, FutureCallback<Response> callback) throws NacosException;
    
    /**
     * close connection.
     */
    public abstract void close();
    
}
