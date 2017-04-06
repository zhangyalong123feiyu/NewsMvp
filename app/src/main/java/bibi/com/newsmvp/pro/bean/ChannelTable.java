package bibi.com.newsmvp.pro.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by bibinet on 2017-1-16.
 */
@Table(name = "channel")
public class ChannelTable {
    @Column(name = "id", isId = true)
    private int id;
    @Column(name = "channelname")
    private String channelname;

    public ChannelTable() {

    }

    public ChannelTable(int id, String channelname) {
        this.id = id;
        this.channelname = channelname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }

    @Override
    public String toString() {
        return "ChannelTable{" +
                "id=" + id +
                ", channelname='" + channelname + '\'' +
                '}';
    }
}
