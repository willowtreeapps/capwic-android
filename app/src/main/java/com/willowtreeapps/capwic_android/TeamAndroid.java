package com.willowtreeapps.capwic_android;

/*********************************************************************
 * Use this class to signal when the next team should start.
 * You can add custom data here if you want the next team to receive it.
 *********************************************************************/
public class TeamAndroid {

    String data;
    boolean isReady;


    public TeamAndroid() {
        this.data = "Some data you could send.";
        this.isReady = false;
    }

    /**
     * Signals to the next team through Realtime Database that you have finished.
     *
     * @param ready - Whether the next team in line can begin.
     */
    public void setReady(boolean ready) {
        this.isReady = ready;
    }

    public boolean getIsReady() {
        return this.isReady;
    }

}
