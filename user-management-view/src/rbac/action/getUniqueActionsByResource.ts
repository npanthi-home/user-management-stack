import RbacState from "../../redux/rbac/state/RbacState";

const getUniqueActionsByResource = (state: RbacState) => {
    const uniqueActionsByResource: Record<string, string[]> = {};

    for (const role in state) {
        const resources = state[role];

        for (const resource in resources) {
            if (!uniqueActionsByResource[resource]) {
                uniqueActionsByResource[resource] = [];
            }

            const actions = resources[resource];
            for (const action in actions) {
                if (actions[action] !== undefined && !uniqueActionsByResource[resource].includes(action)) {
                    uniqueActionsByResource[resource].push(action);
                }
            }
        }
    }

    return uniqueActionsByResource;
};

export default getUniqueActionsByResource;