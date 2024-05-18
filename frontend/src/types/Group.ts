import Match from "./Match";
import Round from "./Round";

interface Group {
    id: number,
    stage: {id: number},
    number: number,
    rounds: Array<Round>,
    matches: Array<Match>
}

export default Group