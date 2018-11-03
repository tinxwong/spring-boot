package com.tinx.java.chipin.core.rule;

import com.tinx.java.chipin.core.Authent;
import com.tinx.java.chipin.entity.ChipinLog;
import com.tinx.java.chipin.entity.Lottery;
import com.tinx.java.chipin.entity.Task;
import com.tinx.java.chipin.entity.UserLottery;
import org.apache.http.client.CookieStore;

/**
 * @author tinx
 * @date 2018-10-4 23:06
 */
public interface ChipinRule extends Rule{

    public int getRuleId();

    public String getRuleName();

    public String getRuleDesc();

    public ChipinLog execute(Authent authent);


    public void setRootUrl(String rootUrl);

    public String getRootUrl();

    public void init(Task task, Lottery lottery, UserLottery userLottery);

}
