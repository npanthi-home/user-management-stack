import React from 'react';
import RbacState from '../../redux/rbac/state/RbacState';
import ResourceConfigurationView from './ResourceConfigurationView';
import { useDispatch } from 'react-redux';
import toggleResource from '../../redux/rbac/toggle_resource/toggleResource';

interface Props {
  rbac: RbacState;
  roles: string[];
  resource: string;
}

const ResourceConfiguration: React.FC<Props> = ({ rbac, roles, resource }) => {
  const getResourceActions = (rbac: RbacState, role: string, resource: string) => Object.values(rbac[role][resource] || {});
  const isResourceChecked = (rbac: RbacState, role: string, resource: string) => getResourceActions(rbac, role, resource).every((value) => value);
  const isResourceIndeterminate = (rbac: RbacState, role: string, resource: string) => getResourceActions(rbac, role, resource).some((value) => value) &&
    !getResourceActions(rbac, role, resource).every((value) => value);

  const dispatch = useDispatch();

  const handleCheckboxChange = (role: string, resource: string) => {
    const permission = `${role}.${resource}`;
    dispatch(toggleResource({ permission }));
  };

  return (
    <ResourceConfigurationView
      rbac={rbac}
      resource={resource}
      roles={roles}
      isResourceChecked={(rbac: RbacState, role: string, resource: string) => isResourceChecked(rbac, role, resource)}
      isResourceIndeterminate={(rbac: RbacState, role: string, resource: string) => isResourceIndeterminate(rbac, role, resource)}
      handleCheckboxChange={handleCheckboxChange}
    />
  );
}

export default ResourceConfiguration;