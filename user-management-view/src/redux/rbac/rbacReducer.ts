import { PayloadAction, createReducer } from '@reduxjs/toolkit';
import fetchPermissions from './fetch_permissions/fetchPermissions';
import savePermissions from './fetch_permissions/savePermissions';
import RbacState from './state/RbacState';
import initialState from './state/initialState';
import toggleAction, { TogglePermissionPayload } from './toggle_action/toggleAction';
import toggleActionReducer from './toggle_action/toggleActionReducer';
import toggleResource from './toggle_resource/toggleResource';
import toggleResourceReducer from './toggle_resource/toggleResourceReducer';

const rbacReducer = createReducer(initialState, (builder) => {
  builder
    .addCase(toggleAction, (state, action: PayloadAction<TogglePermissionPayload>) => {
      return toggleActionReducer(state, action.payload.permission);
    })
    .addCase(toggleResource, (state, action: PayloadAction<TogglePermissionPayload>) => {
      return toggleResourceReducer(state, action.payload.permission);
    })
    .addCase(fetchPermissions.fulfilled, (state, action: PayloadAction<RbacState>) => {
      return action.payload;
    })
    .addCase(fetchPermissions.rejected, (state, action) => {
      return {};
    })
    .addCase(savePermissions.fulfilled, (state, action: PayloadAction<RbacState>) => {
      return action.payload;
    })
    .addCase(savePermissions.rejected, (state, action) => {
      return {};
    })
    .addDefaultCase((state: RbacState) => state);
});

export default rbacReducer;