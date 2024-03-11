import RbacState from "../state/RbacState";

const toggleAction = (state: RbacState, permission: string): RbacState => {
    const [role, resource, actionType] = permission.split('.');

    return {
        ...state,
        [role]: {
            ...state[role],
            [resource]: {
                ...state[role][resource],
                [actionType]: !state[role][resource][actionType],
            },
        },
    };
};

export default toggleAction;

    