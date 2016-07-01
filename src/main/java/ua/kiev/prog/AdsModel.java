package ua.kiev.prog;

import java.util.ArrayList;
import java.util.List;

public class AdsModel {
    private List<Advertisement> ads;
    private long[] ids;

    public AdsModel() {}

    public AdsModel(List<Advertisement> ads) {
        this.ads = ads;
    }

    public List<Advertisement> getAds() {
        return ads;
    }

    public void setAds(List<Advertisement> ads) {
        this.ads = ads;
    }

    public long[] getIds() {
        return ids;
    }

    public void setIds(long[] ids) {
        this.ids = ids;
    }
}
