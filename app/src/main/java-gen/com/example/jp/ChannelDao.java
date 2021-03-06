package com.example.jp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.example.jp.Channel;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CHANNEL".
*/
public class ChannelDao extends AbstractDao<Channel, Long> {

    public static final String TABLENAME = "CHANNEL";

    /**
     * Properties of entity Channel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property SortId = new Property(1, Long.class, "sortId", false, "SORT_ID");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property Type = new Property(3, String.class, "type", false, "TYPE");
        public final static Property Channel = new Property(4, String.class, "channel", false, "CHANNEL");
        public final static Property IsShow = new Property(5, Boolean.class, "isShow", false, "IS_SHOW");
    };


    public ChannelDao(DaoConfig config) {
        super(config);
    }
    
    public ChannelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CHANNEL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"SORT_ID\" INTEGER," + // 1: sortId
                "\"NAME\" TEXT," + // 2: name
                "\"TYPE\" TEXT," + // 3: type
                "\"CHANNEL\" TEXT," + // 4: channel
                "\"IS_SHOW\" INTEGER);"); // 5: isShow
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CHANNEL\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Channel entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long sortId = entity.getSortId();
        if (sortId != null) {
            stmt.bindLong(2, sortId);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(4, type);
        }
 
        String channel = entity.getChannel();
        if (channel != null) {
            stmt.bindString(5, channel);
        }
 
        Boolean isShow = entity.getIsShow();
        if (isShow != null) {
            stmt.bindLong(6, isShow ? 1L: 0L);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Channel readEntity(Cursor cursor, int offset) {
        Channel entity = new Channel( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // sortId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // type
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // channel
            cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5) != 0 // isShow
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Channel entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setSortId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setType(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setChannel(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setIsShow(cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5) != 0);
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Channel entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Channel entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
