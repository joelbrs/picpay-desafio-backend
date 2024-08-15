import { FastifyInstance, RouteHandler } from "fastify";
import { adaptRoute } from "../adapters";
import { makeCreateTransactionController } from "../factories";

export default (router: FastifyInstance): void => {
    router.post("/transfer", adaptRoute(makeCreateTransactionController()));
};
