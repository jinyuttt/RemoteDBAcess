/**    
 * 文件名：SQLModel.java    
 *    
 * 版本信息：    
 * 日期：2018年4月21日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package DBRPC.DBQuery;

import java.util.List;

/**    
 *     
 * 项目名称：DBQuery    
 * 类名称：SQLModel    
 * 类描述：    
 * 创建人：jinyu    
 * 创建时间：2018年4月21日 下午12:22:32    
 * 修改人：jinyu    
 * 修改时间：2018年4月21日 下午12:22:32    
 * 修改备注：    
 * @version     
 *     
 */
public class SQLModel {

    public List<Object> list=null;
    public String sql;
    public String[] sqls;
    public int commitNum;
    public int returnCode;

}
