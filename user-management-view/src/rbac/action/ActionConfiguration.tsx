import React, { useMemo } from 'react';
import { useDispatch } from 'react-redux';
import RbacState from '../../redux/rbac/state/RbacState';
import toggleAction from '../../redux/rbac/toggle_action/toggleAction';
import ActionConfigurationView from './ActionConfigurationView';
import getUniqueActionsByResource from './getUniqueActionsByResource';

interface Props {
    rbac: RbacState;
    roles: string[];
    resource: string;
}


const ActionConfiguration: React.FC<Props> = ({ rbac, roles, resource }) => {
    const dispatch = useDispatch();

    const handleCheckboxChange = (role: string, resource: string, action: string) => {
        const permission = `${role}.${resource}.${action}`;
        dispatch(toggleAction({ permission }));
    };

    const uniqueActionsByResource = useMemo(() => getUniqueActionsByResource(rbac), [rbac]);


    return (
        <ActionConfigurationView
            rbac={rbac}
            roles={roles}
            resource={resource}
            uniqueActionsByResource={uniqueActionsByResource}
            handleCheckboxChange={handleCheckboxChange}
        />
    )
}

export default ActionConfiguration;