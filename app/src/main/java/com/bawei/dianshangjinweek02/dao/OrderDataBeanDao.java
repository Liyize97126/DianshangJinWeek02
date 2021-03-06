package com.bawei.dianshangjinweek02.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.bawei.dianshangjinweek02.util.GoodsConverter;
import java.util.List;

import com.bawei.dianshangjinweek02.bean.OrderDataBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ORDER_DATA_BEAN".
*/
public class OrderDataBeanDao extends AbstractDao<OrderDataBean, Long> {

    public static final String TABLENAME = "ORDER_DATA_BEAN";

    /**
     * Properties of entity OrderDataBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property ShopId = new Property(0, long.class, "shopId", true, "_id");
        public final static Property ShopName = new Property(1, String.class, "shopName", false, "SHOP_NAME");
        public final static Property Checked = new Property(2, boolean.class, "checked", false, "CHECKED");
        public final static Property Cartlist = new Property(3, String.class, "cartlist", false, "CARTLIST");
    }

    private final GoodsConverter cartlistConverter = new GoodsConverter();

    public OrderDataBeanDao(DaoConfig config) {
        super(config);
    }
    
    public OrderDataBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ORDER_DATA_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: shopId
                "\"SHOP_NAME\" TEXT," + // 1: shopName
                "\"CHECKED\" INTEGER NOT NULL ," + // 2: checked
                "\"CARTLIST\" TEXT);"); // 3: cartlist
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ORDER_DATA_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, OrderDataBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getShopId());
 
        String shopName = entity.getShopName();
        if (shopName != null) {
            stmt.bindString(2, shopName);
        }
        stmt.bindLong(3, entity.getChecked() ? 1L: 0L);
 
        List cartlist = entity.getCartlist();
        if (cartlist != null) {
            stmt.bindString(4, cartlistConverter.convertToDatabaseValue(cartlist));
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, OrderDataBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getShopId());
 
        String shopName = entity.getShopName();
        if (shopName != null) {
            stmt.bindString(2, shopName);
        }
        stmt.bindLong(3, entity.getChecked() ? 1L: 0L);
 
        List cartlist = entity.getCartlist();
        if (cartlist != null) {
            stmt.bindString(4, cartlistConverter.convertToDatabaseValue(cartlist));
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public OrderDataBean readEntity(Cursor cursor, int offset) {
        OrderDataBean entity = new OrderDataBean( //
            cursor.getLong(offset + 0), // shopId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // shopName
            cursor.getShort(offset + 2) != 0, // checked
            cursor.isNull(offset + 3) ? null : cartlistConverter.convertToEntityProperty(cursor.getString(offset + 3)) // cartlist
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, OrderDataBean entity, int offset) {
        entity.setShopId(cursor.getLong(offset + 0));
        entity.setShopName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setChecked(cursor.getShort(offset + 2) != 0);
        entity.setCartlist(cursor.isNull(offset + 3) ? null : cartlistConverter.convertToEntityProperty(cursor.getString(offset + 3)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(OrderDataBean entity, long rowId) {
        entity.setShopId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(OrderDataBean entity) {
        if(entity != null) {
            return entity.getShopId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(OrderDataBean entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
