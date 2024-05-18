import ParticipantMatchGameResult from "./ParticipantMatchGameResult"
import ParticipantMatchResullt from "./ParticipantMatchResult"

interface Participant {
    id: number,
    tournamentId: string,
    matchResult: Array<ParticipantMatchResullt>
    matchGameResults: Array<ParticipantMatchGameResult>
}

export default Participant