import { Checkbox, TableCell, TableRow } from '@mui/material';
import React from 'react';
import RbacState from '../../redux/rbac/state/RbacState';

interface ActionConfigurationProps {
    rbac: RbacState;
    roles: string[];
    resource: string;
    uniqueActionsByResource: Record<string, string[]>;
    handleCheckboxChange: (role: string, resource: string, action: string) => void;
}

const ActionConfigurationView: React.FC<ActionConfigurationProps> = ({ rbac, roles, resource, uniqueActionsByResource, handleCheckboxChange }) => {
    return (
        <React.Fragment>
            {uniqueActionsByResource[resource].map((action) => (
                <TableRow key={action}>
                    <TableCell>{action}</TableCell>
                    {roles.map((role) => (
                        <TableCell key={role}>
                            <Checkbox
                                checked={rbac[role][resource][action]}
                                disabled={rbac[role][resource][action] === undefined}
                                onChange={() => handleCheckboxChange(role, resource, action)}
                            />
                        </TableCell>
                    ))}
                </TableRow>
            ))}
        </React.Fragment>
    );
};

export default ActionConfigurationView;
