package edu.nyu.cs101.android.li.michael;

/**
 * Created by JiangJxSrc on 12/2/2017.
 */

public class Dealer extends Gambler{
    public void action() {
        while(getTotal() < 17) {
            proc(HIT);
        }
    }

}
