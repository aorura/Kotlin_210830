package com.lge.sampleapp.javasample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

// @NonNull / @Nullable
// - 용도
// 1) 코틀린에서 타입 추론을 위해 사용됩니다.
//   String / String?
// 2) 안드로이드 컴파일러의 Lint로서 동작합니다.

public class Person {
    private @NonNull
    String name;

    private @Nullable
    String address;

    private String nickname;

    public Person(@NonNull String name,
                  @Nullable String address,
                  String nickname) {
        this.name = null;
        // 'null' is assigned to a variable that is annotated with @NotNull

        int length = address.length();
        // Method invocation 'length' may produce 'NullPointerException'

        this.name = name;
        this.address = address;
        this.nickname = nickname;
    }
}
