package com.passionpham_eng88.basicenglishforyou;

/**
 * Created by passionpham on 2/12/2016.
 */
public class User {
    String stence_;

    public String GetStence()
    {
        return stence_;
    }
    public void SetStence(String stence)
    {
        stence_ = stence;
    }


    public User(String stence) {
        super();
        this.stence_ = stence;
    }
}
