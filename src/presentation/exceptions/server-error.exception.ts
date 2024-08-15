export class ServerErrorException extends Error {
    constructor(stack?: string) {
        super("Internal Server Error");
        this.name = "ServerErrorException";

        this.stack = stack;
    }
}
