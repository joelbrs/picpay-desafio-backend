import { ExtSendEmail } from "@/data/use-cases";
import { SendEmail } from "@/domain/use-cases";
import { EmailFacadeImpl } from "@/infra/external";

export const makeSendEmail = (): SendEmail => {
    const facade = new EmailFacadeImpl();

    return new ExtSendEmail(facade);
};
