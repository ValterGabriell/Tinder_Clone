package com.example.tinder

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.tinder.Photo.PhotosItem
import com.example.tinder.Users.UsersItem
import com.example.tinder.databinding.ActivityMainBinding
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction

class MainActivity : AppCompatActivity() {

    private val listUser = MutableLiveData<List<UsersItem>>()
    private val listPhoto = MutableLiveData<List<PhotosItem>>()
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        repository.getUsers(listUser)
        repository.getPhotos(listPhoto)

        val cardStackView = findViewById<CardStackView>(R.id.card_stack_view)
        cardStackView.layoutManager = CardStackLayoutManager(this, object : CardStackListener {
            override fun onCardDragging(direction: Direction?, ratio: Float) {
                Log.i("TAG", "Drag")
            }

            override fun onCardSwiped(direction: Direction?) {
                Log.i("TAG", "Swipe")
            }

            override fun onCardRewound() {
                Log.i("TAG", "Rewound")
            }

            override fun onCardCanceled() {
                Log.i("TAG", "Cancel")
            }

            override fun onCardAppeared(view: View?, position: Int) {
                Log.i("TAG", "Appear")
            }

            override fun onCardDisappeared(view: View?, position: Int) {
                Log.i("TAG", "Disappear")
            }
        })



        listUser.observe(this) { users->
            listPhoto.observe(this){photos ->
                cardStackView.adapter = CardStackAdapter(users, photos)
            }
        }

        binding.btnRewind.setOnClickListener {
            cardStackView.rewind()
        }



    }


}
