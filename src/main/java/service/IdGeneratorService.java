package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IdGeneratorService extends Remote {
    String getNextId(String entityType) throws RemoteException;
}