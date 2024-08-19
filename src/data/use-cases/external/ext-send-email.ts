import { SendEmailFacade } from "@/data/protocols";
import { SendEmail } from "@/domain/use-cases";

export class ExtSendEmail implements SendEmail {
    constructor(private readonly sendEmailFacade: SendEmailFacade) {}

    async send() {
        return this.sendEmailFacade.sendEmail();
    }
}
