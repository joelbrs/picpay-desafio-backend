import { FastifyInstance } from "fastify";
import { adaptRoute } from "../adapters";
import { makeCreateTransactionController } from "../factories";
import { payerMiddleware } from "../middlewares";

export default (router: FastifyInstance): void => {
    router.post(
        "/transfer",
        { preHandler: [payerMiddleware] },
        adaptRoute(makeCreateTransactionController())
    );
};
