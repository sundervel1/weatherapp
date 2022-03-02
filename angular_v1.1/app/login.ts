export class Login{
    username:string;
    password:string;
    message:string;
    toString(){
        return this.username+', **** '+this.message;
    }
}