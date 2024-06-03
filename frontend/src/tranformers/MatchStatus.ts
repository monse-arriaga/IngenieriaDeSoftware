import { Status } from "brackets-model";
import MatchStatus from "../types/MatchStatus";

class MatchStatusT {

    to (value?:  MatchStatus): Status {
        switch (value) {
            case MatchStatus.WAITING:
                return 1;
            case MatchStatus.READY:
                return 2;     
            case MatchStatus.RUNNING:
                return  3;     
            case MatchStatus.COMPLETED:
                return 4;     
            case MatchStatus.ARCHIVED:
                return 5;           
            default:
                return 0;
        }
    }

    from (value?: Status): MatchStatus {
        switch (value) {
            case 1:
                return MatchStatus.WAITING;
            case 2:
                return MatchStatus.READY;
            case 3:
                return MatchStatus.RUNNING;
            case 4:
                return MatchStatus.COMPLETED;
            case 5:
                return MatchStatus.ARCHIVED;
            default:
                return MatchStatus.LOCKED;
        }
    }
}

export default new MatchStatusT()