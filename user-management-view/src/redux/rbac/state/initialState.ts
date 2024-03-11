import RbacState from "./RbacState";

const initialState: RbacState = {
    "admin": {
        "resource": {
            "cancel": true,
            "approve": true,
            "create": true,
            "update": true
        }
    },
    // "worker": {

    //     "resource": {
    //         "cancel": false,
    //         "approve": false,
    //         "create": true,
    //         "update": false
    //     }
    // },
    // "supervisor": {
    //     "resource": {
    //         "cancel": true,
    //         "approve": true,
    //         "update": true,
    //         "create": false
    //     }
    // }
};

export default initialState;