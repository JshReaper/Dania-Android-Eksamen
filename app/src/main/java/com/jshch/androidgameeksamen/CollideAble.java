package com.jshch.androidgameeksamen;

public interface CollideAble {
    void OnCollisionEnter(Collider other);

    void OnCollisionStay(Collider other);

    void OnCollisionExit(Collider other);
}
