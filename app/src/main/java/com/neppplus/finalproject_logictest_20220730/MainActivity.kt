package com.neppplus.finalproject_logictest_20220730

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.neppplus.finalproject_logictest_20220730.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    val mLottoNumbers = ArrayList<Int>() // 당첨번호 6개를 담을 목록
    val mLottoNumTextViews = ArrayList<TextView>() // 표시용 텍스트 6개를 담을 목록

//    내 번호 6개는 하드코딩으로 목록에 담아두자.

    val mMyNumbers = arrayListOf( 8, 13, 17, 23, 36, 38 )

//    사용 금액 / 당첨 금액 각각을 변수에 저장하고 활용
    var mUsedMoney = 0
    var mEarnedMoney = 0L  // 20억을 넘어가는 경우가 있으니, 일반 0이 아니라, Long 타입의 0으로 저장

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        자동 구매 버튼이 눌리면 => 사용 금액이 1천만원이 될때 까지 반복

        binding.btnAutoBuy.setOnClickListener {

//            문제점 1. 각 등수별로 몇번 당첨됬는지 알고싶다.
//              => 실제 로직 작성 / UI 반영
//            문제점 2. 반복 속도가 너무 빨라서 => 화면에서 표시 불가. (뻗어있는 식으로 나타남)
//              => 로직 작성 연습 영역 X. 안드로이드 기능 활용 (단순 소개) > 따라 적는 정도만

            while ( mUsedMoney < 10000000 ) {
                makeLottoNumbers()
                checkMyRank()
            }

        }

//        당첨 확인 버튼이 눌리면 => 1~45중 하나의 숫자를 뽑아 텍스트뷰 6개에 각각 표시

        binding.btnCheckLottoNum.setOnClickListener {

//            당첨 번호 생성
            makeLottoNumbers()

//            내가 몇등인지 체크
            checkMyRank()

        }


    }

    override fun setValues() {

//        텍스트뷰 6개를 목록에 담아두자.
        mLottoNumTextViews.add( binding.txtLottoNum01 )
        mLottoNumTextViews.add( binding.txtLottoNum02 )
        mLottoNumTextViews.add( binding.txtLottoNum03 )
        mLottoNumTextViews.add( binding.txtLottoNum04 )
        mLottoNumTextViews.add( binding.txtLottoNum05 )
        mLottoNumTextViews.add( binding.txtLottoNum06 )

    }

//    당첨번호를 만들어주는 함수를 만들자

    fun makeLottoNumbers() {

//            랜덤 숫자 6개를 채우기 전에, 기존의 숫자 목록은 비우고 채우자.
        mLottoNumbers.clear()

//            랜덤 숫자 (1~45) 6개를 먼저 생성 > 목록에 담아두자. (숫자 6개를 담을 목록도 필요함)
//            랜덤 숫자를 6회 반복 생성

        for (i in 0..5) {
//                단순 6회 반복 for문

//                이미 뽑아둔 숫자와 중복인 숫자가 랜덤으로 나온다면, 무효처리. 다시 뽑자.
//                제대로 된 숫자가 나올때까지 무한 반복으로 랜덤 뽑자.

            while (true) {

//                1~45 중의 숫자 하나 랜덤 추출
                val randomNum = (1..45).random()

//                    뽑아둔 숫자중에 같은게 없다면? (중복 X) 목록으로 추가,
//                    무한반복 종료 (다음 숫자 뽑으러 넘어가자)

                if ( !mLottoNumbers.contains( randomNum ) ) {

//                     중복이 아니니 당첨번호 목록으로 추가 (0~5번칸  채움)
                    mLottoNumbers.add( randomNum )
                    break  // 나를 감싸는 반복문을 깨고 탈출 => 무한 반복 종료
                }
            }
        }


//            담아둔 숫자를 정리. (작은 수 ~ 큰 수로 정렬 : order)

        mLottoNumbers.sort() // 코틀린 언어가 제공하는 정렬 기능 활용


//            텍스트뷰 6개를 반복적으로 처리 : 반복문 이용
//            txt01, txt02, txt03... 등등 텍스트뷰 목록을 만들고 하나씩 꺼내다 쓰자.
//            for 활용 > 목록에서 하나씩 꺼내주는 역할.

        mLottoNumTextViews.forEachIndexed { index, lottoNumTxt ->

//                lottoNumTxt 변수에는, txtLottoNum01~06이 순서대로 담기면서 반복 실행됨.
//                + index 변수에는 지금이 몇바퀴째인지 (0,1,2,..,5) 의 숫자가 담겨있게 됨.


//                랜덤을 뽑자마자 > 텍스트뷰에 배치하면, 작은 수 ~ 큰 수 대로 정리되지 않음.
//                별도의 for문을 통해서, 6개의 랜덤 숫자를 만들고 > 정리 한 다음 에 > 텍스트뷰에 반영만 따로 반복.

//                당첨번호 6개를 순서대로 뽑아서 표시
//                반복 순서에 맞는 위치의 랜덤값을 찾아서 텍스트뷰에 순서대로 반영

            lottoNumTxt.text = mLottoNumbers[index].toString()

        }


    }

//    당첨번호와, 내 번호를 비교해주는 함수를 만들자

    fun checkMyRank() {

//        1천원을 사용한 금액으로 기록.
        mUsedMoney += 1000  // += : 뒤에 적는 금액만큼 값을 증가.

        binding.txtUsedMoney.text = "${ "%,d".format(mUsedMoney) } 원"


//        당첨 번호 6개와, 내 번호 6개를 비교해서 => "맞춘 개수"에 따라 등수 판정.

//        몇개를 맞췄는지, 갯수를 구해야 한다.

        var correctCount = 0  // 맞춘 숫자를 찾을때마다 1씩 증가 시킬 예정

//        맞는 숫자 찾기 => 내 번호 하나를 들고 > 당첨번호 6개중에 포함되어있는가? => 내 번호 6개에 반복

        for (myNum  in  mMyNumbers) {

            if (mLottoNumbers.contains( myNum )) {

//                숫자 하나를 (더) 맞췄다!

                correctCount++  // ++ : 기존의 값보다 1 증가시켜서 저장.

            }

        }

//        for문이 끝나면, correctCount 변수에는 맞춘 갯수가 저장되어 있다.

//        맞춘 갯수에 따른 등수 판별
//        6개 : 1등, 5개 : (임시) 3등, 4개 : 4등, 3개 : 5등, 그 이하 : 낙첨

        when (correctCount) {
            6 -> {
//                당첨금 61억 증가
//                cf) 기본 숫자 Int는, -20억 ~ +20억 정도 범위의 숫자만 표현 가능.
//                61억은 Int로 기록하기에는 지면이 부족함. (공간이 모자람) - overflow
//                Long 타입으로 변수와 대입값을 변경

                mEarnedMoney += 6100000000
            }
            5 -> {
//                3등 당첨 : 150만원 당첨금 증액
                mEarnedMoney += 1500000
            }
            4 -> {
                mEarnedMoney += 50000
            }
            3 -> {
                mEarnedMoney += 5000
            }
            else -> {
//                증액 없음. 임시로 비워두자.
            }
        }

//            늘어난 당첨금을 텍스트뷰에도 반영

        binding.txtEarnedMoney.text = "${ "%,d".format(mEarnedMoney) } 원"

    }


}