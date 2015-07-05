package com.example.remoteservicetest;

import com.example.remoteservicetest.ParcelableData;

interface IRemoteService {
    ParcelableData process(in ParcelableData data); 
}