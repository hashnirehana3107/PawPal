package com.example.pawpal.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pawpal.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar

class Orders : AppCompatActivity() {

    data class Order(
        val id: String,
        val date: String,
        val total: String,
        val status: String // Processing, Shipped, Delivered, Cancelled
    )

    private val orders = listOf(
        Order("#MF-10342", "Aug 24, 2025", "Rs 1,948", "Processing"),
        Order("#MF-10321", "Aug 18, 2025", "Rs 699", "Shipped"),
        Order("#MF-10298", "Aug 10, 2025", "Rs 2,499", "Delivered"),
        Order("#MF-10254", "Aug 02, 2025", "Rs 349", "Cancelled")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        setupBottomNav(R.id.nav_history)

        val rv = findViewById<RecyclerView>(R.id.rvOrders)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = OrdersAdapter(orders) { order ->
            Snackbar.make(rv, "Open details for ${order.id}", Snackbar.LENGTH_SHORT).show()
            // TODO: startActivity(Intent(this, OrderDetails::class.java).putExtra("orderId", order.id))
        }

        // Next delivery card action
        findViewById<MaterialCardView>(R.id.cardNextDelivery)?.setOnClickListener {
            Snackbar.make(it, "Tracking delivery…", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setupBottomNav(selected: Int) {
        val bottom = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottom.selectedItemId = selected
        bottom.setOnItemSelectedListener { item ->
            if (item.itemId == selected) return@setOnItemSelectedListener true
            when (item.itemId) {
                R.id.nav_home -> startActivity(Intent(this, Home::class.java))
                R.id.nav_library -> startActivity(Intent(this, Shop::class.java)) // ← Fixed (was pointing to itself)
                R.id.nav_journal -> startActivity(Intent(this, Care::class.java))
                R.id.nav_history -> startActivity(Intent(this, Orders::class.java))
                else -> return@setOnItemSelectedListener false
            }
            overridePendingTransition(0, 0)
            finish()
            true
        }
    }

    class OrdersAdapter(
        private val data: List<Order>,
        private val onClick: (Order) -> Unit
    ) : RecyclerView.Adapter<OrdersAdapter.VH>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_order, parent, false)
            return VH(v, onClick)
        }
        override fun getItemCount() = data.size
        override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(data[position])

        class VH(view: View, private val onClick: (Order) -> Unit) : RecyclerView.ViewHolder(view) {
            private val tvId = view.findViewById<TextView>(R.id.tvOrderId)
            private val tvDate = view.findViewById<TextView>(R.id.tvOrderDate)
            private val tvTotal = view.findViewById<TextView>(R.id.tvOrderTotal)
            private val tvStatus = view.findViewById<TextView>(R.id.tvOrderStatus)
            fun bind(item: Order) {
                tvId.text = item.id
                tvDate.text = item.date
                tvTotal.text = item.total
                tvStatus.text = item.status
                itemView.setOnClickListener { onClick(item) }
            }
        }
    }
}
