//Model that matches the Java model for the server to hold data about our logged in user
export class User {

    constructor(public username: string, public firstname: string, public lastname: string, public id: number, public badges: number, public wins: number, 
        public losses: number, public counter: number, public cTime: number) {

    }

}