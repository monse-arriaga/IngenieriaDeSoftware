package mx.ids.playbit.ui.home.tournaments

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dagger.hilt.android.qualifiers.ApplicationContext
import mx.ids.playbit.databinding.ItemTournamentBinding
import mx.ids.playbit.model.tournament.Tournament
import javax.inject.Inject

/**
 * TournamentAdapter used to create tournament items and set the data
 * @author Leonardo Aguilar Rodríguez
 *  */
class TournamentAdapter @Inject constructor(
    @ApplicationContext private val context: Context
) : RecyclerView.Adapter<TournamentAdapter.TournamentViewHolder>() {

    private var tournaments: List<Tournament> = listOf()
    var onItemClick: ((Tournament) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TournamentViewHolder {
        val binding =
            ItemTournamentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TournamentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TournamentViewHolder, position: Int) {
        val tournament = tournaments[position]
        holder.bind(tournament)
    }

    override fun getItemCount(): Int {
        return tournaments.size
    }

    inner class TournamentViewHolder(private val binding: ItemTournamentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tournament: Tournament) {
            //val bitmap = base64ToBitmap(decompressImageString(tournament.image))
            //binding.ivTournamentImage.setImageBitmap(bitmap)
            binding.tvTournamentName.text = "Nombre: ${tournament.name}"
            binding.tvTournamentPlayers.text = "Players: ${tournament.players}"
            binding.tvType.text = "Tipo: ${tournament.tournamentType}"
            binding.tvPrize.text = "Premio: ${tournament.prize}"
            Glide.with(binding.root.context)
                .load(tournament.image)
                .into(binding.ivTournamentImage)
            binding.root.setOnClickListener {
                onItemClick?.invoke(tournament)
            }
        }

    }

    fun updateTournaments(newTournaments: List<Tournament>) {
        this.tournaments = newTournaments
        notifyDataSetChanged()
    }
}