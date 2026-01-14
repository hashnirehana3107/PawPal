package com.example.pawpal.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pawpal.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class Care : AppCompatActivity() {

    private val mockEntries = mutableListOf(
        JournalEntry("Fed salmon kibble", "#care · Today"),
        JournalEntry("Walked 20 min — good energy", "#care · Yesterday")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_care)

        setupBottomNav(R.id.nav_journal)

        val et = findViewById<EditText>(R.id.etJournal)
        val btnSave = findViewById<View>(R.id.btnSave)
        val rv = findViewById<RecyclerView>(R.id.rvJournal)

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = JournalAdapter(mockEntries)

        btnSave.setOnClickListener {
            val text = et.text?.toString()?.ifBlank { "Care note" } ?: "Care note"
            mockEntries.add(0, JournalEntry(text, "#care · Just now"))
            rv.adapter?.notifyItemInserted(0)
            rv.scrollToPosition(0)
            et.setText("")
        }
    }

    private fun setupBottomNav(selected: Int) {
        val bottom = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottom.selectedItemId = selected

        bottom.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    if (selected != R.id.nav_home) {
                        startActivity(Intent(this, Home::class.java))
                        overridePendingTransition(0, 0)
                        finish()
                    }
                    true
                }
                R.id.nav_library -> {
                    if (selected != R.id.nav_library) {
                        // If your “shop” screen class is Shop::class.java, swap Library::class.java to Shop::class.java
                        startActivity(Intent(this, Shop::class.java))
                        overridePendingTransition(0, 0)
                        finish()
                    }
                    true
                }
                R.id.nav_journal -> true // already here
                R.id.nav_history -> {
                    if (selected != R.id.nav_history) {
                        startActivity(Intent(this, Orders::class.java))
                        overridePendingTransition(0, 0)
                        finish()
                    }
                    true
                }
                else -> false
            }
        }
    }

    data class JournalEntry(val title: String, val meta: String)

    class JournalAdapter(private val data: List<JournalEntry>) :
        RecyclerView.Adapter<JournalAdapter.JournalVH>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalVH {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.care_notes, parent, false)
            return JournalVH(v)
        }

        override fun getItemCount() = data.size
        override fun onBindViewHolder(holder: JournalVH, position: Int) = holder.bind(data[position])

        class JournalVH(view: View) : RecyclerView.ViewHolder(view) {
            private val tvTitle = view.findViewById<TextView>(R.id.tvEntryTitle)
            private val tvMeta = view.findViewById<TextView>(R.id.tvEntryMeta)
            fun bind(item: JournalEntry) { tvTitle.text = item.title; tvMeta.text = item.meta }
        }
    }
}
