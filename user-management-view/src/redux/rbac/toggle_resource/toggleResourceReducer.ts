import RbacState from "../state/RbacState";
import Role from "../state/Role";

const toggleResource = (state: RbacState, permission: string): RbacState => {
    const [role, resource] = permission.split('.');

    const newState: RbacState = JSON.parse(JSON.stringify(state));

    if (newState[role] && newState[role][resource]) {
        if (isCheckedOrIndeterminate(newState, role, resource)) {
            Object.keys(newState[role][resource]).forEach((action) => {
                newState[role][resource][action] = false;
            });
        } else {
            Object.keys(newState[role][resource]).forEach((action) => {
                newState[role][resource][action] = true;
            });
        }
    }

    return newState;
};

export default toggleResource;

function isCheckedOrIndeterminate(newState: Role, role: string, resource: string) {
    return Object.values(newState[role][resource]).some((action) => action);
}

