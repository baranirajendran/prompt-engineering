import React, { useState, useEffect } from 'react';
import axios from 'axios';
import AddIcon from '@mui/icons-material/Add';
import {
  TextField,
  Button,
  Typography,
  Box,
  Chip,
  Stack,
  CircularProgress,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  Container,
  useMediaQuery,
  useTheme,
  Paper
} from '@mui/material';

export default function PromptAnalyzer() {
  const [scenario, setScenario] = useState('');
  const [constraintInput, setConstraintInput] = useState('');
  const [constraints, setConstraints] = useState([]);
  const [audienceLevel, setAudienceLevel] = useState('intermediate');
  const [response, setResponse] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [scenarioError, setScenarioError] = useState("");

  const theme = useTheme();
  const isSmallScreen = useMediaQuery(theme.breakpoints.down('md'));

  useEffect(() => {
    if (response) {
      const el = document.getElementById('results');
      if (el) {
        el.scrollIntoView({ behavior: 'smooth' });
      }
    }
  }, [response]);

  const handleAddConstraint = () => {
    if (constraintInput.trim()) {
      setConstraints([...constraints, constraintInput.trim()]);
      setConstraintInput('');
    }
  };

  const handleRemoveConstraint = (index) => {
    setConstraints(constraints.filter((_, i) => i !== index));
  };

  const handleSubmit = async () => {
    if (scenario.trim().split(" ").length < 8) {
        setScenarioError("Scenario must be at least 9 words.");
        return;
      }
  
      if (scenario.trim().length > 1000) {
        setScenarioError("Scenario is too long. Keep under 1000 characters.");
        return;
      }
  
      if (constraints.length < 2) {
        setError("Please provide at least 2 constraints.");
        return;
      }

      setScenarioError("");
      setError("");
      setLoading(true);
      setResponse(null);
    try {
      const res = await axios.post('/api/analyze-scenario',{
        scenario,
        constraints: [...constraints, `Audience Level: ${audienceLevel}`],
      });
      setResponse(res.data);
    } catch (error) {
      console.error('Error fetching analysis:', error);
      setError("Error generating response. Please try again.");
      alert('Error generating response. Please try again.');
    } finally {
      setLoading(false);
    }
  };

  const renderStrategies = () => {
    const sections = [];
    let currentSection = [];
    let currentTitle = '';

    response.proposedStrategies?.forEach((line) => {
      if (line.startsWith('Pitfall:')) {
        if (currentTitle) {
          sections.push({ title: currentTitle, items: currentSection });
        }
        currentTitle = line;
        currentSection = [];
      } else {
        currentSection.push(line);
      }
    });
    if (currentTitle) {
      sections.push({ title: currentTitle, items: currentSection });
    }

    return sections.map((section, idx) => (
      <Box key={idx} sx={{ mb: 3 }}>
        <Typography variant="subtitle1" sx={{ fontWeight: 600, color: '#f5f5f5' }}>
          {section.title}
        </Typography>
        <ul style={{ marginLeft: '1.5em' }}>
          {section.items.map((item, i) => (
            <li key={i}>{item}</li>
          ))}
        </ul>
      </Box>
    ));
  };

  return (
    <Box sx={{ backgroundColor: '#121212', minHeight: '100vh', py: 4 }}>
      <Container maxWidth="xl">
        <Typography variant="h4" gutterBottom textAlign="center" color="#f0f0f0">
          Prompt Engineering Assessment Tool
        </Typography>

        <Box
          display="flex"
          flexDirection={isSmallScreen ? 'column' : 'row'}
          gap={4}
          justifyContent="space-between"
        >
          {/* Left Container */}
          <Paper elevation={3} sx={{ flex: 1, p: 4, backgroundColor: '#2a2a2a', color: '#e0e0e0' }}>
            <Typography variant="h6" gutterBottom>Enter Scenario & Constraints</Typography>

            <TextField
              label="Scenario"
              placeholder="Describe your situation/problem here"
              multiline
              minRows={4}
              fullWidth
              margin="normal"
              value={scenario}
              onChange={(e) => setScenario(e.target.value)}
              error={!!scenarioError}
              helperText={scenarioError}
              InputProps={{ style: { color: '#e0e0e0' } }}
              InputLabelProps={{ style: { color: '#bbbbbb' } }}
              sx={{ '& .MuiOutlinedInput-root': { '& fieldset': { borderColor: '#555' } } }}
            />

            <FormControl fullWidth margin="normal">
              <InputLabel id="audience-label" sx={{ color: '#bbbbbb' }}>Audience Level</InputLabel>
              <Select
                labelId="audience-label"
                id="audience-select"
                value={audienceLevel}
                label="Audience Level"
                onChange={(e) => setAudienceLevel(e.target.value)}
                sx={{ color: '#e0e0e0', borderColor: '#555' }}
              >
                <MenuItem value="beginner">Beginner</MenuItem>
                <MenuItem value="intermediate">Intermediate</MenuItem>
                <MenuItem value="expert">Expert</MenuItem>
              </Select>
            </FormControl>

            <Stack direction="row" spacing={2} alignItems="center" mt={2}>
              <TextField
                label="Add Constraint"
                value={constraintInput}
                onChange={(e) => setConstraintInput(e.target.value)}
                InputProps={{ style: { color: '#e0e0e0' } }}
                InputLabelProps={{ style: { color: '#bbbbbb' } }}
                sx={{ '& .MuiOutlinedInput-root': { '& fieldset': { borderColor: '#555' } } }}
              />
              <Button variant="contained" onClick={handleAddConstraint}><AddIcon /></Button>
            </Stack>

            <Stack direction="row" spacing={1} flexWrap="wrap" mt={2}>
              {constraints.map((item, index) => (
                <Chip
                  key={index}
                  label={item}
                  onDelete={() => handleRemoveConstraint(index)}
                  sx={{ mb: 1, bgcolor: '#444', color: 'white' }}
                />
              ))}
            </Stack>

            <Button
              variant="contained"
              color="primary"
              onClick={handleSubmit}
              disabled={loading}
              sx={{ mt: 4 }}
              fullWidth
            >
              {loading ? <CircularProgress size={24} /> : 'Submit'}
            </Button>
          </Paper>

          {/* Right Container */}
          <Paper elevation={3} sx={{ flex: 1, p: 4, backgroundColor: '#2a2a2a', color: '#e0e0e0' }} id="results">
            {response ? (
              <Box>
                <Typography variant="h6" gutterBottom>Scenario Summary</Typography>
                <Typography>{response.scenarioSummary}</Typography>

                <Typography variant="h6" mt={3}>Potential Pitfalls</Typography>
                <ul>
                  {response.potentialPitfalls?.map((item, idx) => (
                    <li key={idx}>{item}</li>
                  ))}
                </ul>

                <Typography variant="h6" mt={3}>Proposed Strategies</Typography>
                {renderStrategies()}

                <Typography variant="h6" mt={3}>Recommended Resources</Typography>
                {response.recommendedResourcesStructured &&
                  Object.entries(response.recommendedResourcesStructured).map(([section, items], idx) => (
                    <Box key={idx} sx={{ mb: 2 }}>
                      <Typography variant="subtitle1" sx={{ fontWeight: 600 }}>
                        {section.replace(/_/g, ' ').toUpperCase()}
                      </Typography>
                      <ul>
                        {items.map((res, i) => (
                          <li key={i}>{res}</li>
                        ))}
                      </ul>
                    </Box>
                  ))}

                <Typography variant="h6" mt={3}>Disclaimer</Typography>
                <Typography>{response.disclaimer}</Typography>
              </Box>
            ) : (
              <Typography variant="body1">Submit a prompt to view analysis.</Typography>
            )}
          </Paper>
        </Box>
      </Container>
    </Box>
  );
}