///*
// * File Name: UserTableDB.java
// * History:
// * Created by Administrator on 2015-11-4
// */
//package com.yuepang.yuepang.db;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Environment;
//import android.text.TextUtils;
//
//import com.yuepang.yuepang.Util.FileUtils;
//import com.yuepang.yuepang.Util.LogUtils;
//import com.yuepang.yuepang.model.UserInfo;
//
//import java.io.File;
//import java.io.FileFilter;
//
//public class AnzhiExternalDB {
//
//    private final int DB_VER = 1;// 相比初始版本增加 表shortcut， 表user 增加account列
//
//    private static final String ICON_PACKAGENAME = "package_name";// 包名
//
//    private static final String ICON_GAMENAME = "game_name";// 游戏名
//
//    private final static String TABLE_USER_SHORTCUT = "shortcut";//
//
//    private final static String TABLE_USER = "user";// 用户信息表
//
//    private final static String TABLE_DEVICE = "device";// 当前登录状态表
//
//
//    public static String FIELD_TEL = "tel";// 手机号
//
//    public static String FIELD_NAME = "name";// 姓名
//
//    public static String FIELD_PWD = "pwd";// 密码
//
//    public static String FIELD_BIR = "bir";// 生日
//
//    public static String FIELD_SEX = "sex";//性别
//
//    public static String FIELD_URL = "headUrl";//头像地址
//
//    public static String FIELD_ID = "id";//身份证号
//
//
//    private static final String TAG = "db_tag";
//
//    public static AnzhiExternalDB sInstance;
//
//    private SQLiteDatabase mDdatbase;
//
//    private Context mContext;
//
//    private AnzhiExternalDB(Context context) {
//        mContext = context.getApplicationContext();
//    }
//
//    public static synchronized AnzhiExternalDB getInstance(Context context) {
//        if (sInstance == null) {
//            sInstance = new AnzhiExternalDB(context);
//        }
//        return sInstance;
//    }
//
//    /**
//     * 插入当前登录的账号
//     *
//     * @param user
//     */
//    public void insertUser(UserInfo user) {
//        SQLiteDatabase db = createSQLiteDatabase();
//        if (db == null) {
//            return;
//        }
//        Cursor cur = null;
//        try {
//            ContentValues values = new ContentValues();
//            cur = db.query(TABLE_USER, new String[]{FIELD_NAME}, null, null, null, null, null);
//            values.put(FIELD_TEL, user.getTel());
//            values.put(FIELD_PWD, user.getPwd());
//            values.put(FIELD_NAME, user.getName());
//            values.put(FIELD_SEX, user.getTel());
//            values.put(FIELD_BIR, user.getTel());
//            values.put(FIELD_ID, user.getTel());
//            values.put(FIELD_URL, user.getHeaderImgUrl());
//            int rows = db.update("user", values, "tel=?", new String[] { user.getTel() });
//            if (rows == 0) {
//                db.insert("user", null, values);
//            }
//        } catch (Exception e) {
//            LogUtils.e(e);
//        } finally {
//            if (cur != null) {
//                cur.close();
//            }
//        }
//    }
//
//    /**
//     * 跟新当前的表
//     */
//    private void changeSessionId(UserInfo user) {
//        SQLiteDatabase db = createSQLiteDatabase();
//        if (db == null) {
//            return;
//        }
//        String des3Account = getEncryptStr(user.getAccount(), SECRET_KEY);// 加密用户名
//        try {
//            ContentValues values = new ContentValues();
//            values.put(FIELD_SESSIONID, user.getSessionId());
//            values.put(FIELD_SESSIONSIGN, user.getSessionSign());
//            db.update(TABLE_USER, values, FIELD_ACCOUNT + "=?", new String[]{des3Account});
//        } catch (Exception e) {
//            LogUtils.e(e);
//        } finally {
//
//        }
//
//    }
//
//    /**
//     * 根据用户名或者登录名有效的sid
//     */
//    public String getCurrentSid(String account, String name) {
//        String sid = getsidbyAccount(account);
//        if (!TextUtils.isEmpty(sid)) {
//            return sid;
//        }
//        return getSidByloginName(name);
//    }
//
//    /**
//     * 插入当前的icon信息
//     *
//     * @param info
//     */
//    public void insertIcon(ShortCutInfo info) {
//        SQLiteDatabase db = createSQLiteDatabase();
//        if (db == null) {
//            return;
//        }
//        try {
//            ContentValues values = new ContentValues();
//            values.put(ICON_GAMENAME, info.getGameName());
//            values.put(ICON_PACKAGENAME, info.getPackageName());
//            db.insert(TABLE_USER_SHORTCUT, null, values);
//        } catch (Exception e) {
//            LogUtils.e("", e);
//        }
//    }
//
//    /**
//     * 获得该用户名最新的sid
//     */
//    public String getSidByloginName(String name) {
//        if (TextUtils.isEmpty(name)) {
//            return null;
//        }
//        String desName = getEncryptStr(name, SECRET_KEY);//
//        SQLiteDatabase db = createSQLiteDatabase();
//        if (db == null) {
//            return null;
//        }
//        Cursor cur = null;
//        try {
//            cur = db.query(TABLE_USER, null, "name=?", new String[]{desName}, null, null, null);
//            if (cur != null && cur.getCount() > 0) {
//                cur.moveToFirst();
//                return cur.getString(cur.getColumnIndex(FIELD_SID));
//            }
//        } catch (Exception e) {
//            LogUtils.e("", e);
//        } finally {
//            if (cur != null) {
//                cur.close();
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 获得该用户名最新的usertoken
//     */
//    public String getUsertokenByloginName(String name) {
//        if (TextUtils.isEmpty(name)) {
//            return null;
//        }
//        String desName = getEncryptStr(name, SECRET_KEY);//
//        SQLiteDatabase db = createSQLiteDatabase();
//        if (db == null) {
//            return null;
//        }
//        Cursor cur = null;
//        try {
//            cur = db.query(TABLE_USER, null, "name=?", new String[]{desName}, null, null, null);
//            if (cur != null && cur.getCount() > 0) {
//                cur.moveToFirst();
//                return getOriginalStr(cur.getString(cur.getColumnIndex(FIELD_USERTOKEN)), SECRET_KEY);
//            }
//        } catch (Exception e) {
//            LogUtils.e("", e);
//        } finally {
//            if (cur != null) {
//                cur.close();
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 获得改用户名的最新的sid； 4.1.1 新加的方法
//     */
//    private String getsidbyAccount(String account) {
//        if (TextUtils.isEmpty(account)) {
//            return null;
//        }
//        String desacconut = getEncryptStr(account, SECRET_KEY);//
//        SQLiteDatabase db = createSQLiteDatabase();
//        if (db == null) {
//            return null;
//        }
//        Cursor cur = null;
//        try {
//            cur = db.query(TABLE_USER, null, FIELD_ACCOUNT + "=?", new String[]{desacconut}, null, null, null);
//            if (cur != null && cur.getCount() > 0) {
//                cur.moveToFirst();
//                return cur.getString(cur.getColumnIndex(FIELD_SID));
//            }
//        } catch (Exception e) {
//            LogUtils.e("", e);
//        } finally {
//            if (cur != null) {
//                cur.close();
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 获得sessionId的最新的sid； 4.1.1 新加的方法
//     */
//    public String getsessionIdByAccount(String account) {
//        if (TextUtils.isEmpty(account)) {
//            return null;
//        }
//        String desacconut = getEncryptStr(account, SECRET_KEY);//
//        SQLiteDatabase db = createSQLiteDatabase();
//        if (db == null) {
//            return null;
//        }
//        Cursor cur = null;
//        try {
//            cur = db.query(TABLE_USER, null, FIELD_ACCOUNT + "=?", new String[]{desacconut}, null, null, null);
//            if (cur != null && cur.getCount() > 0) {
//                cur.moveToFirst();
//                return cur.getString(cur.getColumnIndex(FIELD_SESSIONID));
//            }
//        } catch (Exception e) {
//            LogUtils.e("", e);
//        } finally {
//            if (cur != null) {
//                cur.close();
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 获得sessionSign的最新的sid； 4.1.1 新加的方法
//     */
//    public String getsessionSignByAccount(String account) {
//        if (TextUtils.isEmpty(account)) {
//            return null;
//        }
//        String desacconut = getEncryptStr(account, SECRET_KEY);//
//        SQLiteDatabase db = createSQLiteDatabase();
//        if (db == null) {
//            return null;
//        }
//        Cursor cur = null;
//        try {
//            cur = db.query(TABLE_USER, null, FIELD_ACCOUNT + "=?", new String[]{desacconut}, null, null, null);
//            if (cur != null && cur.getCount() > 0) {
//                cur.moveToFirst();
//                return cur.getString(cur.getColumnIndex(FIELD_SESSIONSIGN));
//            }
//        } catch (Exception e) {
//            LogUtils.e("", e);
//        } finally {
//            if (cur != null) {
//                cur.close();
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 根据用户名和uid删除相应记录
//     */
//    public void removeUser(String name) {
//        SQLiteDatabase db = createSQLiteDatabase();
//        if (db == null) {
//            return;
//        }
//        try {
//            String uesrSid = getSidByloginName(name);
//            String deviceSid = getSessionToken();
//            if (uesrSid != null && deviceSid != null && uesrSid.equals(deviceSid)) {
//                db.delete(TABLE_DEVICE, null, null);
//            }
//            db.delete("user", "name=?", new String[]{getEncryptStr(name, SECRET_KEY)});// 删除帐号列表中的记录
//        } catch (Exception e) {
//            LogUtils.e("", e);
//        }
//    }
//
//    /**
//     * 根据用户名和游戏名删除相应记录
//     */
//    public void removeIcon(String pageName) {
//        SQLiteDatabase db = createSQLiteDatabase();
//        if (db == null) {
//            return;
//        }
//        try {
//            db.delete(TABLE_USER_SHORTCUT, "package_name=?", new String[]{pageName});// 删除共享账号中的记录
//        } catch (Exception e) {
//            LogUtils.e("", e);
//        }
//
//    }
//
//    /**
//     * 插入共享账号至device表中
//     */
//    public void insertShareUser(UserInfo info) {
//        SQLiteDatabase db = createSQLiteDatabase();
//        if (db == null) {
//            return;
//        }
//        try {
//            ContentValues values = new ContentValues();
//            values.put(FIELD_UID, "");// 置空uid字段
//            values.put(FIELD_SID, info.getSid());
//            int rows = db.update("device", values, null, null);
//            if (rows == 0) {
//                db.insert("device", null, values);
//            }
//            setCurrentUser(info.getLoginName());
//        } catch (Exception e) {
//            LogUtils.e("", e);
//        }
//    }
//
//    /**
//     * 根据登录名设置当前账号的登录状态
//     */
//    public void setCurrentUser(String name) {
//        SQLiteDatabase db = createSQLiteDatabase();
//        if (db == null) {
//            return;
//        }
//        try {
//            ContentValues values = new ContentValues();
//            values.put(FIELD_STATE, "1");
//            db.update(TABLE_USER, values, "name=?", new String[]{getEncryptStr(name, SECRET_KEY)});
//            values = new ContentValues();
//            values.put(FIELD_STATE, "0");
//            db.update(TABLE_USER, values, "name<>?", new String[]{getEncryptStr(name, SECRET_KEY)});
//
//        } catch (Exception e) {
//            LogUtils.e(e);
//        }
//    }
//
//    public List<UserInfo> getAllUserInfo() {
//        List<UserInfo> infos = new ArrayList<UserInfo>();
//        SQLiteDatabase db = createSQLiteDatabase();
//        if (db == null) {
//            return infos;
//        }
//        Cursor cur = null;
//        try {
//            cur = db.query("user", null, null, null, null, null, FIELD_LOGINTIME + " desc");
//            if (cur != null && cur.getCount() > 0) {
//                cur.moveToFirst();
//                for (int i = 0; i < cur.getCount(); i++) {
//                    UserInfo info = new UserInfo();
//                    String loginName = getOriginalStr(cur.getString(cur.getColumnIndex("name")), SECRET_KEY);
//
//                    if (loginName.endsWith("@qq") || loginName.endsWith("@weibo")) {
//                        cur.moveToNext();
//                        continue;
//                    } else {
//                        info.setLoginName(loginName);
//                    }
//                    info.setUserToken(getOriginalStr(cur.getString(cur.getColumnIndex(FIELD_USERTOKEN)), SECRET_KEY));
//                    info.setLoginTime(cur.getLong(cur.getColumnIndex(FIELD_LOGINTIME)));
//                    info.setSid(cur.getString(cur.getColumnIndex(FIELD_SID)));
//                    infos.add(info);
//                    cur.moveToNext();
//                }
//            }
//        } catch (Exception e) {
//            LogUtils.e(TAG, e);
//        } finally {
//            if (cur != null) {
//                cur.close();
//            }
//        }
//        return infos;
//    }
//
//    private SQLiteDatabase createSQLiteDatabase() {
//        return createSQLiteDatabase(true);
//    }
//
//    private synchronized SQLiteDatabase createSQLiteDatabase(boolean allowRetry) {
//        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/yuepang");
//        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
//            if (mDdatbase != null) {
//                mDdatbase.close();
//                mDdatbase = null;
//            }
//            String[] paths = SysUtils.getStoragePath(mContext.getSystemService("storage"), true);
//            if (paths != null && paths.length >= 2) {
//                dir = new File(paths[1] + "yuepang");
//            } else {
//                return null;
//            }
//        }
//        if (!dir.exists()) {
//            if (mDdatbase != null) {
//                mDdatbase.close();
//                mDdatbase = null;
//            }
//            dir.mkdirs();
//        }
//        if (!FileUtils.isWriteable(dir.getPath()) || !FileUtils.isReadable(dir.getPath()) || !dir.isDirectory()) {
//            LogUtils.w(dir.getPath() + " is can not readable!!");
//            dir.delete();
//            return null;
//        }
//        File file = new File(dir.getPath() + "/yuepangdb");
//        if (mDdatbase != null && mDdatbase.isOpen()) {
//            if (file.exists()) {
//                return mDdatbase;
//            } else {
//                mDdatbase.close();
//            }
//        }
//        try {
//            if (!file.exists() && file.createNewFile()) {
//                mDdatbase = SQLiteDatabase.openOrCreateDatabase(file.getAbsolutePath(), null);
//                createTable();
//                mDdatbase.setVersion(getDBVersion());
//                LogUtils.d("ucenter db ver: " + mDdatbase.getVersion());
//                return mDdatbase;
//            }
//            if (!file.canRead() || !file.canWrite() || !file.isFile()) {
//                LogUtils.w(file.getPath() + "is can not readable!!");
//                file.delete();
//                return null;
//            }
//            mDdatbase = SQLiteDatabase.openDatabase(file.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
//            int oldVersion = mDdatbase.getVersion();
//            if (oldVersion < getDBVersion()) {
//                onUpgrade(oldVersion, getDBVersion());
//                mDdatbase.setVersion(getDBVersion());
//            }
//            checkTableValid(getDBVersion(), file);
//            LogUtils.d("user db ver: " + mDdatbase.getVersion());
//        } catch (Throwable e) {
//            LogUtils.e(e);
//            try {
//                deletePushDatabase(file);
//            } catch (Exception e1) {
//
//            }
//            if (allowRetry) {
//                return createSQLiteDatabase(false);
//            }
//        }
//        return mDdatbase;
//    }
//
//    public boolean isExistByGameName(String gameName) {
//        SQLiteDatabase db = createSQLiteDatabase();
//        if (db == null) {
//            return false;
//        }
//        Cursor cur = null;
//        try {
//            cur = db.query(TABLE_USER_SHORTCUT, null, "game_name=?", new String[]{gameName}, null, null, null);
//            if (cur != null && cur.getCount() > 0) {
//                return true;
//            }
//        } catch (Exception e) {
//            LogUtils.e(TAG, e);
//        } finally {
//            if (cur != null) {
//                cur.close();
//            }
//        }
//        return false;
//    }
//
//    private void createTable() {
//        String createTableUser = "create table if not exists " + TABLE_USER + "("
//                + "_id integer primary key AutoIncrement," + FIELD_STATE + " text," + FIELD_NAME + " text,"
//                + FIELD_USERTOKEN + " text," + FIELD_SESSIONSIGN + " text," + FIELD_SID + " text," + FIELD_UID
//                + " text," + FIELD_SERVICE_ID + " text," + FIELD_LOGINTIME + " text," + FIELD_SESSIONID + " text,"
//                + FIELD_ACCOUNT + " text" + ")";
//        LogUtils.d(createTableUser);
//        try {
//            mDdatbase.execSQL(createTableUser);
//        } catch (Exception e) {
//            LogUtils.e(e);
//        }
//
//        String createTableDevice = "create table if not exists " + TABLE_DEVICE + "("
//                + "_id integer primary key AutoIncrement," + FIELD_UID + " text," + FIELD_SID + " text,"
//                + FIELD_SERVICE_ID + " text," + FIELD_TEL + " text" + ")";
//        LogUtils.d(createTableDevice);
//
//        try {
//            mDdatbase.execSQL(createTableDevice);
//            LogUtils.d("userdb db ver: " + mDdatbase.getVersion());
//        } catch (Exception e) {
//            LogUtils.e(e);
//        }
//
//        String createTableIcon = "create table if not exists " + TABLE_USER_SHORTCUT + "("
//                + "_id integer primary key AutoIncrement," + ICON_GAMENAME + " text," + ICON_PACKAGENAME + " text"
//                + ")";
//        try {
//            mDdatbase.execSQL(createTableIcon);
//            LogUtils.d("userdb db ver: " + mDdatbase.getVersion());
//        } catch (Exception e) {
//            LogUtils.e(e);
//        }
//
//    }
//
//    private int getDBVersion() {
//        return 2;
//    }
//
//    private void onUpgrade(int oldVersion, int newVersion) throws Exception {
//        LogUtils.d("usercenter db onUpgrade oldVersion= " + oldVersion + ", newVersion= " + newVersion);
//        if (newVersion > oldVersion || newVersion >= 2) {
//            String createTableIcon = "create table if not exists " + TABLE_USER_SHORTCUT + "("
//                    + "_id integer primary key AutoIncrement," + ICON_GAMENAME + " text," + ICON_PACKAGENAME + " text"
//                    + ")";
//            try {
//                mDdatbase.execSQL(createTableIcon);
//                mDdatbase.execSQL("alter table " + TABLE_USER + " add " + FIELD_SESSIONSIGN + " TEXT"); // 在device表中增加数据库
//                mDdatbase.execSQL("alter table " + TABLE_USER + " add " + FIELD_ACCOUNT + " TEXT");
//                mDdatbase.execSQL("alter table " + TABLE_USER + " add " + FIELD_SESSIONID + " TEXT");
//                LogUtils.d("userdb db ver: " + mDdatbase.getVersion());
//            } catch (Exception e) {
//                LogUtils.e(e);
//            }
//        }
//    }
//
//    private void checkTableValid(int version, File file) throws Exception {
//        Cursor cur = mDdatbase.query(TABLE_USER, null, null, null, null, null, null);
//        cur.close();
//    }
//
//    public boolean deletePushDatabase(File file) throws Exception {
//        if (file == null) {
//            return false;
//        }
//
//        boolean deleted = false;
//        deleted |= file.delete();
//        deleted |= new File(file.getPath() + "-journal").delete();
//        deleted |= new File(file.getPath() + "-shm").delete();
//        deleted |= new File(file.getPath() + "-wal").delete();
//
//        File dir = file.getParentFile();
//        if (dir != null) {
//            final String prefix = file.getName() + "-mj";
//            File[] files = dir.listFiles(new FileFilter() {
//                @Override
//                public boolean accept(File candidate) {
//                    return candidate.getName().startsWith(prefix);
//                }
//            });
//            if (files != null) {
//                for (File masterJournal : files) {
//                    deleted |= masterJournal.delete();
//                }
//            }
//        }
//        return deleted;
//    }
//
//    /**
//     * 获得最近的登录账号的sid
//     */
//    public String getCurrentSid() {
//        String sid = getSessionToken();// 为了适配3.2 以前的 版本必须先从 device 表中获得 sid
//        if (!TextUtils.isEmpty(sid)) {
//            return sid;
//        }
//        List<UserInfo> list = getAllUserInfo();
//        if (list != null && list.size() > 0) {
//            for (UserInfo info : list) {
//                if (!TextUtils.isEmpty(info.getSid())) {
//                    return info.getSid();
//                }
//            }
//        }
//        return sid;
//    }
//
//    /**
//     * 获得当前设备最近的登录账号，兼容智友和市场
//     */
//    public UserInfo getCurrentUser() {
//        SQLiteDatabase db = createSQLiteDatabase();
//        if (db == null) {
//            return null;
//        }
//        Cursor cur = null;
//        try {
//            cur = db.query(TABLE_USER, null, "state=?", new String[]{"1"}, null, null, null);
//            if (cur != null && cur.getCount() > 0) {
//                cur.moveToFirst();
//                UserInfo user = new UserInfo();
//                user.setLoginName(getOriginalStr(cur.getString(cur.getColumnIndex("name")), SECRET_KEY));
//                user.setUserToken(getOriginalStr(cur.getString(cur.getColumnIndex("sid")), SECRET_KEY));
//                return user;
//            }
//        } catch (Exception e) {
//            LogUtils.e("", e);
//        } finally {
//            if (cur != null) {
//                cur.close();
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 获得当前的共享账号的 sid
//     */
//    public String getSessionToken() {
//        SQLiteDatabase db = createSQLiteDatabase();
//        if (db == null) {
//            return null;
//        }
//        Cursor cur = null;
//        try {
//            cur = db.query("device", new String[]{"sessiontoken"}, null, null, null, null, null);
//            if (cur != null && cur.getCount() > 0) {
//                cur.moveToFirst();
//                return cur.getString(cur.getColumnIndex("sessiontoken"));
//            }
//        } catch (Exception e) {
//            LogUtils.e(TAG, e);
//        } finally {
//            if (cur != null) {
//                cur.close();
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 普通数据解密方法
//     */
//    public static String getOriginalStr(String st, String key) {
//        if (TextUtils.isEmpty(st)) {
//            return "";
//        }
//        if (key != null) {
//            return Des3Util.decrypt(st, key);
//        }
//        return "";
//    }
//
//    /**
//     * 普通数据加密方法
//     */
//    public static String getEncryptStr(String st, String key) {
//        if (TextUtils.isEmpty(st)) {
//            return "";
//        }
//        if (key != null) {
//            return Des3Util.encrypt(st, key);
//        }
//        return "";
//    }
//
//}
