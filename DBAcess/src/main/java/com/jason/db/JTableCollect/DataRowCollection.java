/**    
 * 文件名：DataRowCollection.java    
 *    
 * 版本信息：    
 * 日期：2018年3月17日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.jason.db.JTableCollect;

import java.util.ArrayList;

/**    
 *     
 * 项目名称：DBAcess    
 * 类名称：DataRowCollection    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2018年3月17日 下午3:31:24    
 * 修改人：jinyu    
 * 修改时间：2018年3月17日 下午3:31:24    
 * 修改备注：    
 * @version     
 *     
 */
public class DataRowCollection extends ArrayList<DataRow> {
    /**    
     * serialVersionUID:   
     *    
     */    
    
    private static final long serialVersionUID = 1L;
    /**
     * DataRowCollection所屬的DataTable，唯讀
     */
    private JDataTable Table;

    /**
     * DataRowCollection被建立時，一定要指定所屬的DataTable
     * @param table 
     */
    public DataRowCollection(JDataTable table)
    {
     this.Table = table;
    
    }
    
    /**
     * 取得所屬的DataTable
     * @return DataTable
     */
    public JDataTable getTable()
    {
     return this.Table;
    }

  
    
}
