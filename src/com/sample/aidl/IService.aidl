package com.sample.aidl;

import com.sample.aidl.IServiceCallBack;

interface IService {
    /** Request the process ID of this service, to do evil things with it. */
    int getPid();

    void fromActivity();
    
    void registerCallBack(IServiceCallBack cb);
}