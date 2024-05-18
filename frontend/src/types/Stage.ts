import Group from "./Group";
import Match from "./Match";
import MatchGame from "./MatchGame";
import Round from "./Round";
import StageType from "./StageType";

interface Stage {
    id: number,
    tournamentId: number,
    name: string,
    type: StageType
    stageSettings: {id: number},
    number: number,
    groups: Array<Group>,
    rounds: Array<Round>,
    matches: Array<Match>,
    matchGames: Array<MatchGame>
}

export default Stage