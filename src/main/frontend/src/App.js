import React from 'react';
import PromptAnalyzer from './PromptAnalyzer';
import { CssBaseline, ThemeProvider, createTheme } from '@mui/material';

const theme = createTheme({
  palette: {
    mode: 'light', // You can change to 'dark' for dark mode
    primary: {
      main: '#1976d2',
    },
    background: {
      default: '#f9f9f9',
    },
  },
});

function App() {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <PromptAnalyzer />
    </ThemeProvider>
  );
}

export default App;