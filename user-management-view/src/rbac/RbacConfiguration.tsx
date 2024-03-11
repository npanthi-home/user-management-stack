import React, { useEffect, useMemo } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../redux/RootState';
import fetchPermissions from '../redux/rbac/fetch_permissions/fetchPermissions';
import RbacConfigurationView from './RbacConfigurationView';
import savePermissions from '../redux/rbac/fetch_permissions/savePermissions';

const RbacConfiguration: React.FC = () => {
  const dispatch = useDispatch();
  const rbac = useSelector((state: RootState) => state.rbac);
  const roles = useMemo(() => Object.keys(rbac), [rbac]);
  const resources = useMemo(() => Array.from(new Set(roles.flatMap((role) => Object.keys(rbac[role] || {})))), [rbac, roles]);

  useEffect(() => {
    dispatch<any>(fetchPermissions());
  }, [dispatch]);

  return (
    <RbacConfigurationView
      rbac={rbac}
      roles={roles}
      resources={resources}
      handleSaveClick={() => dispatch<any>(savePermissions(rbac))}
    />
  );
};

export default RbacConfiguration;