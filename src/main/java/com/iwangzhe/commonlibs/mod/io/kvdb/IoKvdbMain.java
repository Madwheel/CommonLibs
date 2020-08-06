package com.iwangzhe.commonlibs.mod.io.kvdb;


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;
import com.iwangzhe.commonlibs.base.ModMain;
import com.iwangzhe.commonlibs.mod.Mgr;
import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.KeyIterator;
import com.snappydb.SnappydbException;

import java.io.Serializable;

public class IoKvdbMain extends ModMain {
    public String getModName() {
        return "IoKvdbMain";
    }

    private static IoKvdbMain instance = new IoKvdbMain();

    private IoKvdbMain() {
    }

    public static IoKvdbMain getInstance() {
        return instance;
    }

    private DB mGlobalDb;

    public void born() {
        super.born();
        try {
            //https://github.com/EsotericSoftware/kryo#compatiblefieldserializer
            //向前向后兼容模式
            //可以新增字段
            Kryo kryo = new Kryo();
            kryo.setDefaultSerializer(CompatibleFieldSerializer.class);
            mGlobalDb = DBFactory.open(Mgr.getmContext(), "kv.global", kryo);
        } catch (SnappydbException e) {
            mGlobalDb = new MyDB();
            d("snappydbException", e.toString());
        }
    }

    public void terminate() {
        if (mGlobalDb != null) {
            try {
                mGlobalDb.close();
            } catch (SnappydbException e) {
                d(e.toString());
            }
        }
    }

    public DB getGlobalDb() {
        return mGlobalDb;
    }

}

class MyDB implements DB {
    @Override
    public void close() throws SnappydbException {

    }

    @Override
    public void destroy() throws SnappydbException {

    }

    @Override
    public boolean isOpen() throws SnappydbException {
        return false;
    }

    @Override
    public void put(String key, byte[] data) throws SnappydbException {
        put();
    }

    @Override
    public void put(String key, String value) throws SnappydbException {
        put();
        return;
    }

    private void put() throws SnappydbException {
        throw new SnappydbException("My DB is null!");
    }

    @Override
    public void put(String key, Serializable value) throws SnappydbException {
        put();
    }

    @Override
    public void put(String key, Serializable[] value) throws SnappydbException {
        put();
    }

    @Override
    public void put(String key, Object object) throws SnappydbException {
        put();
    }

    @Override
    public void put(String key, Object[] object) throws SnappydbException {
        put();
    }

    @Override
    public void putInt(String key, int val) throws SnappydbException {
        put();
    }

    @Override
    public void putShort(String key, short val) throws SnappydbException {
        put();
    }

    @Override
    public void putBoolean(String key, boolean val) throws SnappydbException {
        put();
    }

    @Override
    public void putDouble(String key, double val) throws SnappydbException {
        put();
    }

    @Override
    public void putFloat(String key, float val) throws SnappydbException {
        put();
    }

    @Override
    public void putLong(String key, long val) throws SnappydbException {
        put();
    }

    @Override
    public void del(String key) throws SnappydbException {

    }

    @Override
    public String get(String key) throws SnappydbException {
        return null;
    }

    @Override
    public byte[] getBytes(String key) throws SnappydbException {
        return new byte[0];
    }

    @Override
    public <T extends Serializable> T get(String key, Class<T> className) throws SnappydbException {
        return null;
    }

    @Override
    public <T> T getObject(String key, Class<T> className) throws SnappydbException {
        return null;
    }

    @Override
    public <T extends Serializable> T[] getArray(String key, Class<T> className) throws SnappydbException {
        return null;
    }

    @Override
    public <T> T[] getObjectArray(String key, Class<T> className) throws SnappydbException {
        return null;
    }

    @Override
    public short getShort(String key) throws SnappydbException {
        return 0;
    }

    @Override
    public int getInt(String key) throws SnappydbException {
        return 0;
    }

    @Override
    public boolean getBoolean(String key) throws SnappydbException {
        return false;
    }

    @Override
    public double getDouble(String key) throws SnappydbException {
        return 0;
    }

    @Override
    public long getLong(String key) throws SnappydbException {
        return 0;
    }

    @Override
    public float getFloat(String key) throws SnappydbException {
        return 0;
    }

    @Override
    public boolean exists(String key) throws SnappydbException {
        return false;
    }

    @Override
    public String[] findKeys(String prefix) throws SnappydbException {
        return new String[0];
    }

    @Override
    public String[] findKeys(String prefix, int offset) throws SnappydbException {
        return new String[0];
    }

    @Override
    public String[] findKeys(String prefix, int offset, int limit) throws SnappydbException {
        return new String[0];
    }

    @Override
    public int countKeys(String prefix) throws SnappydbException {
        return 0;
    }

    @Override
    public String[] findKeysBetween(String startPrefix, String endPrefix) throws SnappydbException {
        return new String[0];
    }

    @Override
    public String[] findKeysBetween(String startPrefix, String endPrefix, int offset) throws SnappydbException {
        return new String[0];
    }

    @Override
    public String[] findKeysBetween(String startPrefix, String endPrefix, int offset, int limit) throws SnappydbException {
        return new String[0];
    }

    @Override
    public int countKeysBetween(String startPrefix, String endPrefix) throws SnappydbException {
        return 0;
    }

    @Override
    public KeyIterator allKeysIterator() throws SnappydbException {
        return null;
    }

    @Override
    public KeyIterator allKeysReverseIterator() throws SnappydbException {
        return null;
    }

    @Override
    public KeyIterator findKeysIterator(String prefix) throws SnappydbException {
        return null;
    }

    @Override
    public KeyIterator findKeysReverseIterator(String prefix) throws SnappydbException {
        return null;
    }

    @Override
    public KeyIterator findKeysBetweenIterator(String startPrefix, String endPrefix) throws SnappydbException {
        return null;
    }

    @Override
    public KeyIterator findKeysBetweenReverseIterator(String startPrefix, String endPrefix) throws SnappydbException {
        return null;
    }

    @Override
    public Kryo getKryoInstance() {
        return null;
    }
}
