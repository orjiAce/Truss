package com.example.truss.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.afollestad.viewpagerdots.DotsIndicator
import com.example.truss.R
import kotlinx.android.synthetic.main.custom_layout.view.*

class MainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    lateinit var  dots: DotsIndicator
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val welcomeTXT = findViewById<TextView>(R.id.welcome_txt)
        val slideUp = AnimationUtils.loadAnimation(this, R.anim.fadein)
        welcomeTXT.startAnimation(slideUp)

//login button
            val  loginBtn = findViewById<View>(R.id.login_btn) as Button
        //signup button.
        val signUp = findViewById<Button>(R.id.register_btn)

        //method to take you to login screen
        loginBtn.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            val option = ActivityOptionsCompat.makeSceneTransitionAnimation(this)

        startActivity(loginIntent, option.toBundle())
        }

        signUp.setOnClickListener {
            val signUpIntent = Intent(this, SignUpActivity::class.java)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
            startActivity(signUpIntent, options.toBundle())

        }

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = mSectionsPagerAdapter


dots = findViewById(R.id.dots)
                dots.attachViewPager(viewPager)

    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1)
        }

        override fun getCount(): Int {
            // Show 5 total pages.(we will use 5 pages so change it to 5)
            return 3
        }
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    class PlaceholderFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val rootView = inflater.inflate(R.layout.custom_layout, container, false)
            /*since our views are in fragment_main.xml which is inflated in rootView
              so we have to write rootView.oursomeview
              otherwise it will try to find the view in activity_main.xml so app will crash*/
            //handle swipe/slide
            if (arguments!!.getInt(ARG_SECTION_NUMBER) == 1){
                //set title to title_tv
               // rootView.title_tv.text = "Title One"
                //set image to image_iv
                rootView.imageView.setImageResource(R.drawable.img1)
                //set description to description_tv
                rootView.description_tv.text = "Pic events you are interested in and we will tailor them to you"
            }
            if (arguments!!.getInt(ARG_SECTION_NUMBER) == 2){
                //set title to title_tv
             //   rootView.title_tv.text = "Title Two"
                //set image to image_iv
                rootView.imageView.setImageResource(R.drawable.img2)
                //set description to description_tv
                rootView.description_tv.text = "Get notified about events"
            }
            if (arguments!!.getInt(ARG_SECTION_NUMBER) == 3){
                //set title to title_tv
               // rootView.title_tv.text = "Title Three"
                //set image to image_iv
                rootView.imageView.setImageResource(R.drawable.img3)
                //set description to description_tv
                rootView.description_tv.text = "Get automated tickets sent to yout email containing unique QR Code"
            }


            return rootView
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_SECTION_NUMBER = "section_number"

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }




}
