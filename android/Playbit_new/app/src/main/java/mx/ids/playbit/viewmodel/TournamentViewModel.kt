package mx.ids.playbit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import mx.ids.playbit.model.tournament.TournamentItem
import javax.inject.Inject

@HiltViewModel
class TournamentViewModel @Inject constructor() : ViewModel() {

    private val _tournaments = MutableLiveData<List<TournamentItem>>()
    val tournaments: LiveData<List<TournamentItem>> get() = _tournaments

    fun setTournaments(items: List<TournamentItem>) {
        _tournaments.value = items
    }
}