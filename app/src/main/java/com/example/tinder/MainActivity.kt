package com.example.tinder

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.tinder.Adapter.CardStackAdapter
import com.example.tinder.Photo.PhotosItem
import com.example.tinder.Repository.Repository
import com.example.tinder.Users.UsersItem
import com.example.tinder.databinding.ActivityMainBinding
import com.yuyakaido.android.cardstackview.*

class MainActivity : AppCompatActivity() {

    private val listUser = MutableLiveData<List<UsersItem>>()
    private val listPhoto = MutableLiveData<List<PhotosItem>>()
    private val repository = Repository()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository.apply {
            this.getPhotos(listPhoto)
            this.getUsers(listUser)
        }

        binding.cardStackView.layoutManager = configureCardStackManager()
        observeDataFromRepository(binding.cardStackView)
        rewindUser()


    }


    private fun rewindUser() {
        binding.btnRewind.setOnClickListener {
            binding.cardStackView.rewind()
        }
    }

    private fun configureCardStackManager(): CardStackLayoutManager {
        val cardStackLayoutManager = CardStackLayoutManager(this, object : CardStackListener {
            override fun onCardDragging(direction: Direction?, ratio: Float) {

            }

            override fun onCardSwiped(direction: Direction?) {
                Toast.makeText(this@MainActivity, "onCardSwiped", Toast.LENGTH_SHORT).show()
            }

            override fun onCardRewound() {
                Toast.makeText(this@MainActivity, "onCardRewound", Toast.LENGTH_SHORT).show()
            }

            override fun onCardCanceled() {
                Toast.makeText(this@MainActivity, "onCardCanceled", Toast.LENGTH_SHORT).show()
            }

            override fun onCardAppeared(view: View?, position: Int) {
                Toast.makeText(this@MainActivity, "onCardAppeared", Toast.LENGTH_SHORT).show()
            }

            override fun onCardDisappeared(view: View?, position: Int) {
                Toast.makeText(this@MainActivity, "onCardDisappeared", Toast.LENGTH_SHORT).show()
            }
        })

        cardStackLayoutManager.apply {
            this.setVisibleCount(2)
            this.setStackFrom(StackFrom.Left)
            this.setMaxDegree(20.0f)
            this.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        }

        return cardStackLayoutManager
    }



    private fun observeDataFromRepository(cardStackView: CardStackView) {
        listUser.observe(this) { users ->
            listPhoto.observe(this) { photos ->
                cardStackView.adapter = CardStackAdapter(users, photos)
            }
        }
    }


}
