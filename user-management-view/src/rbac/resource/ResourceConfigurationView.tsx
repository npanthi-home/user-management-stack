import React from 'react';
import { Checkbox, TableCell, TableRow } from '@mui/material';
import ActionConfiguration from '../action/ActionConfiguration';
import RbacState from '../../redux/rbac/state/RbacState';

interface PresentationProps {
    rbac: RbacState;
    resource: string;
    roles: string[];
    isResourceChecked: (rbac: RbacState, role: string, resource: string) => boolean;
    isResourceIndeterminate: (rbac: RbacState, role: string, resource: string) => boolean;
    handleCheckboxChange: (role: string, resource: string) => void;
}

const ResourceConfigurationView: React.FC<PresentationProps> = ({
    rbac,
    resource,
    roles,
    isResourceChecked,
    isResourceIndeterminate,
    handleCheckboxChange,
}) => {
    return (
        <React.Fragment key={resource}>
            <TableRow>
                <TableCell><b>{resource}</b></TableCell>
                {roles.map((role) => (
                    <TableCell key={role}>
                        <Checkbox
                            checked={isResourceChecked(rbac, role, resource)}
                            indeterminate={isResourceIndeterminate(rbac, role, resource)}
                            onChange={() => handleCheckboxChange(role, resource)}
                        />
                    </TableCell>
                ))}
            </TableRow>

            <ActionConfiguration rbac={rbac} roles={roles} resource={resource} />
        </React.Fragment>
    );
};

export default ResourceConfigurationView;
