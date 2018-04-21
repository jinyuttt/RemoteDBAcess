/**    
 * 文件名：DBExecute.java    
 *    
 * 版本信息：    
 * 日期：2018年3月18日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package DBRPC.DBQuery;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import fastRpc.jason.core.RPCMap;

/**    
 *     
 * 项目名称：DBQuery    
 * 类名称：DBExecute    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2018年3月18日 上午2:21:33    
 * 修改人：jinyu    
 * 修改时间：2018年3月18日 上午2:21:33    
 * 修改备注：    
 * @version     
 *     
 */
public class DBExecute {
    private static   ExecutorService serive=Executors.newCachedThreadPool();
    @RPCMap(name="query")
    public ReturnResult queryData(SQLModel params)
    {
        ReturnResult r=null;
        final SQLModel model=params;
        FutureTask<ReturnResult> task=new FutureTask<ReturnResult>(new Callable<ReturnResult>() {
            public ReturnResult call() throws Exception {
               return query(model);
            }
        });
        serive.execute(task);
        try {
          r=  task.get(5, TimeUnit.MINUTES);
        } catch (Exception e) {
            r=new ReturnResult();
            r.error=e.getMessage();
            task.cancel(true);
        }
        return r;
    }
    private ReturnResult query(SQLModel params)
    {
        DBBil db=new DBBil();
        ReturnResult r=null;
       r=db.queryTable(params);
        return r;
    }
    @RPCMap(name="querySql")
    public void recSQLFile(String params)
    {
        SQLModel model=new SQLModel();
        model.sql=params;
        query(model);
        System.out.println("querySql");
    }
    @RPCMap(name="executeDML")
    public ReturnResult executeDML(SQLModel params)
    {
        DBBil db=new DBBil();
         return db.executeDML(params);
        
    }
    @RPCMap(name="executeBatch")
    public ReturnResult executeBatch(SQLModel params)
    {
        DBBil db=new DBBil();
        if(params.sqls==null||params.sqls.length==0)
        {
         return db.executeBatch(params);
        }
        else
        {
           return  db.executeBatchSQL(params);
        }
    }
    @RPCMap(name="querySigle")
    public ReturnResult querySigle(SQLModel params)
    {
        DBBil db=new DBBil();
        return db.executeSigle(params);
    }
    @RPCMap(name="sqlFile")
    public void recSQLFile(SQLModel params)
    {
        DBBil db=new DBBil();
         db.recSQLFile(params);
    }
}
