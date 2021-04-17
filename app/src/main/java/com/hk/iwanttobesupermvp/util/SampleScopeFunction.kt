package com.hk.iwanttobesupermvp.util

import com.hk.iwanttobesupermvp.R

object SampleScopeFunction {
    // scope functions
    /**
     * 스코프 함수 let , apply , also , run , with 들의 원형 (이름만 앞에 sample을 붙인거지 기존의 스코프함수와 동일하게 작동)
     *
     * let : 주로 null검사를 진행할 때 자주 사용하는 스코프 함수
     *       그 외로는 스트림 처리를 할 떄 사용한다.
     *       반환되는 객체는 함수 리터럴 즉 block안에 들어간 함수를 의미 , 매개변수의 형식은 수신자 객체를 의미하게 된다
     *
     * apply : view 혹은 widget을 초기화 할때 많이 사용합니다
     *         또한 초기화 하면서 기본적으로 적용해야할 여러 메소드를 호출하거나 속성을 수정할 떄 사용한다.
     *
     * also : 한 객체의 초기화 외애 여러 작업을 하고 싶을 때
     *        Builder 패턴을 이용해 객체 구성하는 등의 처리 과정 중에 특정한 작업을 하려는 경우 적합
     */
    inline fun <T,R> T.sampleLet(block : (T) -> R) : R = block(this)
    // T의 형태를 받아 R의 형태로 반환을 하는 함수
    // 근데 block을 실행할때 this를 달고 갑니다
    // 이때 저 this 함수를 진행하는 T입니다.
    // 그러므로 블록이 실행되는 매개변수의 형식은 수신자 객체
    // 반환되는 것은 함수 즉 block이 됩니다.

    inline fun <T> T.sampleApply(block : T.() -> Unit) : T {
        block()
        return this
    }
    // T의 형태를 받아 Unit을 반환합니다.
    // block을 실행하는데 그 블록의 함수는 T의 함수들이며 그 실행한 후에 다시 T를 반환하게 되는데
    // block을 실행하는동안에 T의 상태가 변화되어 다시 재탄생 하는 느낌의 함수라고 생각하면 편합니다.
    // 더 좋은 말로는 T이 수신자 객체인 컨텍스트 안에서 필요한 메소드를 호출해 적용시킬 수 있다는 의미입니다.

    inline fun <T> T.sampleAlso(block : (T) -> Unit) : T {
        block(this)
        return this
    }
    // 위의 apply의 함수 형태와 굉장히 유사하게 되어 있습니다.
    // 이걸 좀 생각해보면 apply의 경우 T의 함수들을 호출해 적용시키는 것이였다면
    // also는 수신자 객체인 T를 매개변수로 끔 하여 사용하는 것입니다.
    // 즉 T의 메소드를 호출하는 것이 아닌 다른 함수를 진행하는데 T가 필요할 경우에 사용한다고 생각하면 됩니다

    inline fun <T,R> T.sampleRun(block : T.() -> R) : R = block()
    inline fun <T,R> sampleWith(receiver : T, block : T.() -> R) : R = receiver.block()
}