import { CrudInterface, Table } from "brackets-manager";
import StageService from "../services/StageService";
import { Group, Match, MatchGame, Round, Stage } from "brackets-model";
import GroupService from "../services/GroupService";
import RoundService from "../services/RoundService";
import MatchGameService from "../services/MatchGameService";
import MatchService from "../services/MatchService";

export class tournamentStorage implements CrudInterface {


    public insert<T>(table: Table, value: T): Promise<number>;
    public insert<T>(table: T, values: T[]): Promise<boolean>;
    
    public async insert<T>(table: Table, values: T | T[]): Promise<number | boolean> {
        const isArray = Array.isArray(values)
        try {
            switch (table) {
                case "stage":
                    StageService.create(values as Stage)
                    break;
                case "group":
                    GroupService.create(values as Group);
                    break;
                case "round":
                    RoundService.create(values as Round)
                    break;
                case "match":
                    MatchService.create(values as Match)
                    break;
                case "match_game":
                    MatchGameService.create(values as MatchGame);
                    break;
                case "participant":
                    console.log("Processing participant table...");
                    break;
                default:
                    console.log("Unknown table");
            }
        } catch (error) {
            return isArray ? false : -1;
        }
        return isArray ? true : 1;
    }

    public select<T>(table: Table): Promise<T[] | null>;
    public select<T>(table: Table, id: number): Promise<T | null>;
    public select<T>(table: Table, filter: Partial<T>): Promise<T[] | null>;

    public async select<T>(table: Table, filter?: number | Partial<T>): Promise< T |  T[] | null> {
        try {
            switch (table) {
                case "stage":
                    return StageService.select(filter) as T;
                case "group":
                    return GroupService.select(filter) as T;
                case "round":
                    return RoundService.select(filter) as T;
                case "match":
                    return MatchService.select(filter) as T;
                case "match_game":
                    return MatchGameService.select(filter) as T;
                case "participant":
                    return null;
                default:
                    return null;
            }
        } catch (error) {
            return null;
        }
    }

    public update<T>(table: Table, id: number, value: T): Promise<boolean>;
    public update<T>(table: Table, filter: Partial<T>, value: Partial<T>): Promise<boolean>;
 
    public async update<T>(table: Table, filter: number | Partial<T>, value: T | Partial<T>): Promise<boolean> {
        try {
            switch (table) {
                case "stage":
                    StageService.update(filter, value as Stage)
                    break;
                case "group":
                    GroupService.update(filter, value as Group)
                    break;
                case "round":
                    RoundService.update(filter, value as Round)
                    break;
                case "match":
                    MatchService.update(filter, value as Match)
                    break;
                case "match_game":
                    MatchGameService.update(filter, value as MatchGame)
                    break;
                case "participant":
                    console.log("Processing participant table...");
                    break;
                default:
                    console.log("Unknown table");
            }
        } catch (error) {
            return false;
        }
        return true;    
    }

    public delete<T>(table: Table): Promise<boolean>;
    public delete<T>(table: Table, filter: Partial<T>): Promise<boolean>;
    
    public async delete<T>(table: Table, filter?: T | Partial<T>): Promise<boolean> {
        try {
            switch (table) {
                case "stage":
                    StageService.delete(filter as object)
                    break;
                case "group":
                    GroupService.delete(filter as object)
                    break;
                case "round":
                    RoundService.delete(filter as object)
                    break;
                case "match":
                    MatchService.delete(filter as object)
                    break;
                case "match_game":
                    MatchGameService.delete(filter as object)
                    break;
                case "participant":
                    console.log("Processing participant table...");
                    break;
                default:
                    console.log("Unknown table");
            }
        } catch (error) {
            return false;
        }
        return true;  
    }

}