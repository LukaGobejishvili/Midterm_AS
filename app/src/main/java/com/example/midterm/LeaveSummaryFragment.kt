package com.example.midterm

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class LeaveSummaryFragment : Fragment() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: LeaveTabPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_leave_summary, container, false)

        tabLayout = view.findViewById(R.id.tabLayout)
        viewPager = view.findViewById(R.id.viewPager)
        val emptyLeaveCard = view.findViewById<View>(R.id.emptyLeaveCard)
        val submitButton = view.findViewById<Button>(R.id.buttonSubmitLeave)

        adapter = LeaveTabPagerAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Review"
                1 -> "Approved"
                2 -> "Rejected"
                else -> ""
            }
        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> emptyLeaveCard.visibility = View.GONE
                    1, 2 -> emptyLeaveCard.visibility = View.VISIBLE
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        submitButton.setOnClickListener {
            showSubmitDialog()
        }

        return view
    }

    private fun showSubmitDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_submit_leave, null)

        val alertDialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setCancelable(true)
            .create()

        val confirmBtn = dialogView.findViewById<Button>(R.id.buttonConfirm)
        val cancelBtn = dialogView.findViewById<Button>(R.id.buttonCancel)

        confirmBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Leave Submitted!", Toast.LENGTH_SHORT).show()
            alertDialog.dismiss()
        }

        cancelBtn.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        alertDialog.show()
    }
}
